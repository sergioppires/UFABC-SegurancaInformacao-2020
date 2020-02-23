import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class aula1 {

    public static String cifrar(String texto, String chave) {
        String resultado = "";
        for (int i = 0, j = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    resultado += (char) ((c + chave.toUpperCase().charAt(j) - 2 * 'A') % 26 + 'A');
                } else {
                    resultado += (char) ((c + chave.toLowerCase().charAt(j) - 2 * 'a') % 26 + 'a');
                }
            } else {
                resultado += c;
            }
            j = ++j % chave.length();
        }
        return resultado;
    }

    public static String decifrar(String texto, String chave) {
        String result = "";
        for (int i = 0, j = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result += ((char) ('Z' - (25 - (c - chave.toUpperCase().charAt(j))) % 26));
                } else {
                    result += ((char) ('z' - (25 - (c - chave.toLowerCase().charAt(j))) % 26));
                }
            } else {
                result += c;
            }
            j = ++j % chave.length();
        }
        return result;
    }

    public static List<String> lerArquivos() {
        List<String> lista = new ArrayList<>();
        String arquivo1 = "Arquivos/texto1.txt";
        String arquivo2 = "Arquivos/texto2.txt";
        try (Stream<String> stream = Files.lines(Paths.get(arquivo1))) {
            lista.add(stream.collect(Collectors.toList()).get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> stream = Files.lines(Paths.get(arquivo2))) {
            lista.add(stream.collect(Collectors.toList()).get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static void main(String[] args) {
        String key = "chavadevignere";
        String text1 = lerArquivos().get(0);
        String text2 = lerArquivos().get(1);

        System.out.println("Texto 1");
        System.out.println("O texto cifrado:");
        String textoCifrado1 = cifrar(text1, key);
        System.out.println(textoCifrado1);
        System.out.println("O texto decifrado:");
        System.out.println(decifrar(textoCifrado1, key));

        System.out.println("Texto 2");
        System.out.println("O texto cifrado:");
        String textoCifrado2 = cifrar(text2, key);
        System.out.println(textoCifrado2);
        System.out.println("O texto decifrado:");
        System.out.println(decifrar(textoCifrado2, key));
    }
}