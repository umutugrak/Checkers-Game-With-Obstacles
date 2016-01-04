package checkers;

import java.util.Arrays;
import model.CheckersBoard;

public class CheckersTool implements ICheckersConstants
{
    private CheckersTool()
    {
        
    }
    
    public static CheckersBoard copy(CheckersBoard board)
    {
        int[][] cells = board.getCells();
        int[][] newCells = new int[cells.length][cells[0].length];
        for (int i = 0;i < cells.length;i++) {
            newCells[i] = Arrays.copyOf(cells[i], cells[i].length);
        }
        
        CheckersBoard newBoard = new CheckersBoard(newCells);
        return newBoard;
    }
    
    public static void printBoard(CheckersBoard board)
    {
        int[][] cells = board.getCells();
        String string = new String();
        for(int y = 0; y < cells[0].length;y++)
        {
            for (int x = 0; x < cells.length; x++)
            {
                switch(cells[x][y])
                {
                case EMPTY: string += '_'; break;
                case BLOCK: string += 'X'; break;
                case BLACK: string += 'B'; break;
                }
            }
            string += '\n';
        }
        System.out.println(string);
    }
}
