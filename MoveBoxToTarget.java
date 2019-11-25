class Solution {
    
    class MazeState {
        int playerX;
        int playerY;
        int boxX;
        int boxY;
        int estimate;
        int moves;
        
        MazeState(int px, int py, int bx, int by, int e, int m) {
            this.playerX = px;
            this.playerY = py;
            this.boxX = bx;
            this.boxY = by;
            this.estimate = e;
            this.moves = m;
        }

        @Override
        public int hashCode() {
            return Objects.hash(playerX, playerY, boxX, boxY);
        }
        
        @Override
        public boolean equals(Object o) {
            if(o == this) return true;
            if(o == null || o.getClass() != getClass()) return false;
            MazeState that = (MazeState) o;
            return (this.boxX == that.boxX) &&
                (this.boxY == that.boxY) &&
                (this.playerX == that.playerX) &&
                (this.playerY == that.playerY);
            
        }
        
        int boxToPlayerManhattanDist() {
            return Math.abs(boxX - playerX) + Math.abs(boxY - playerY);
        }
        
        @Override
        public String toString() {
            return playerX + " " + playerY + " " + boxX + " " + boxY + " " + estimate + " " + moves;
        }
    }
    
    public int minPushBox(char[][] grid) {
        
        int destX = 0, destY = 0, startX = 0, startY = 0, playerX = 0, playerY = 0;
        int[] moveX = {-1, 1, 0, 0};
        int[] moveY = {0, 0, 1, -1};
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0 ; j < grid[0].length; j++) {
                if(grid[i][j] == 'S') {
                    playerX = i;
                    playerY = j;
                } else if(grid[i][j] == 'B') {
                    startX = i;
                    startY = j;
                } else if(grid[i][j] == 'T') {
                    destX = i;
                    destY = j;
                }
            }
        }
        Set<MazeState> visitedStates = new HashSet<>();
        PriorityQueue<MazeState> heuristicHeap = new PriorityQueue<MazeState>((a,b) -> {
            if(a.estimate != b.estimate) {
                return Integer.compare(a.estimate, b.estimate);
            }
            return Integer.compare(a.boxToPlayerManhattanDist(), b.boxToPlayerManhattanDist());
        });
        
        MazeState initState = new MazeState(playerX, playerY, startX, startY, Math.abs(destX - startX) + Math.abs(destY - startY), 0);
        
        heuristicHeap.add(initState);
        visitedStates.add(initState);
        
        while(!heuristicHeap.isEmpty()) {
           
            MazeState currState = heuristicHeap.poll();

            if(currState.boxX == destX && currState.boxY == destY) {
                return currState.moves;
            }
            
            for(int i = 0 ; i < moveX.length; i++) {
                int newX = currState.playerX + moveX[i];
                int newY = currState.playerY + moveY[i];
                int boxX = currState.boxX;
                int boxY = currState.boxY;
                int updatedMoves = currState.moves;
                if(newX == boxX && newY == boxY) {
                    boxX += moveX[i];
                    boxY += moveY[i];
                    updatedMoves += 1;
                }
                int updatedEstimate = Math.abs(destX - boxX) + Math.abs(destY - boxY) + updatedMoves;
                MazeState newState = new MazeState(newX, newY, boxX, boxY, updatedEstimate, updatedMoves);
                if(validCoordinate(newX, newY, grid) && validCoordinate(boxX, boxY, grid) && !visitedStates.contains(newState)) {
                    heuristicHeap.add(newState);
                    visitedStates.add(newState);
                }
            }

        }
        
        return -1;
    }
    
    private boolean validCoordinate(int x, int y, char[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != '#';
    }
}