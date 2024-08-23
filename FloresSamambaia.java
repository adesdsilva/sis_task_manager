public class FloresSamambaia {

    private final DistanciaStrategy distanciaStrategy;

    // Construtor que recebe a estratégia de cálculo de distância
    public FloresSamambaia(DistanciaStrategy distanciaStrategy) {
        this.distanciaStrategy = distanciaStrategy;
    }

    public String tentativaDesenhar(int r1, int x1, int y1, int r2, int x2, int y2) {
        // Calcula a distância entre os centros dos dois círculos usando a estratégia
        double distanciaCentros = distanciaStrategy.calcularDistancia(x1, y1, x2, y2);

        // Verifica se o círculo do caçador engloba completamente o círculo da flor
        if (distanciaCentros + r2 <= r1) {
            return "RICO";
        } else {
            return "MORTO";
        }
    }

    // Implementação da estratégia de distância euclidiana
    private static class DistanciaEuclidianaStrategy implements DistanciaStrategy {
        @Override
        public double calcularDistancia(int x1, int y1, int x2, int y2) {
            return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }
    }

    // Interface para a estratégia de cálculo de distância
    private interface DistanciaStrategy {
        double calcularDistancia(int x1, int y1, int x2, int y2);
    }

    public static void main(String[] args) {
        // Instancia a classe com a estratégia de distância euclidiana
        FloresSamambaia florSamambaia = new FloresSamambaia(new DistanciaEuclidianaStrategy());

        // Testes
        System.out.println(florSamambaia.tentativaDesenhar(5, 0, 0, 3, 0, 0)); // RICO
        System.out.println(florSamambaia.tentativaDesenhar(5, 0, 0, 5, 0, 0)); // RICO
        System.out.println(florSamambaia.tentativaDesenhar(5, 0, 0, 6, 0, 0)); // MORTO
        System.out.println(florSamambaia.tentativaDesenhar(10, 0, 0, 1, 8, 8)); // MORTO
    }
}

