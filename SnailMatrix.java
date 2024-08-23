
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SnailMatrix {

    private final MatrixTraversalStrategy traversalStrategy;

    // Construtor que recebe a estratégia de percurso
    public SnailMatrix(MatrixTraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    public List<Integer> snail(int[][] matrix) {
        return traversalStrategy.traverse(matrix);
    }

    private interface MatrixTraversalStrategy {
        List<Integer> traverse(int[][] matrix);
    }

    private static class SnailTraversalStrategy implements MatrixTraversalStrategy {
        @Override
        public List<Integer> traverse(int[][] matrix) {
            // Validação da matriz para garantir que não seja nula ou vazia
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return Collections.emptyList(); // Retorna uma lista vazia se a matriz for inválida
            }

            List<Integer> result = new ArrayList<>();
            int top = 0;
            int bottom = matrix.length - 1;
            int left = 0;
            int right = matrix[0].length - 1;

            while (top <= bottom && left <= right) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                top++;

                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                right--;

                if (top <= bottom) {
                    for (int i = right; i >= left; i--) {
                        result.add(matrix[bottom][i]);
                    }
                    bottom--;
                }

                if (left <= right) {
                    for (int i = bottom; i >= top; i--) {
                        result.add(matrix[i][left]);
                    }
                    left++;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        // Instancia a classe com a estratégia de percurso snail
        SnailMatrix snailMatrix = new SnailMatrix(new SnailTraversalStrategy());

        // Exemplos de matrizes para teste
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        // Testes
        System.out.println(snailMatrix.snail(matrix1)); // [1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(snailMatrix.snail(matrix2)); // [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]
    }
}
