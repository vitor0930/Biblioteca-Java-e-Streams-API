import java.util.*;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;

public class Biblioteca {
    private Map<Integer, User> register = new HashMap<>();
    private Map<User, List<Livro>> loanedBooksByUser = new HashMap();
    private List<Livro> books = new ArrayList<>();
    DoubleSummaryStatistics stats;

    public void registerUser(User usuario) {
        this.register.put(usuario.getId(), usuario);
    }

    public void registerBook(Livro livro) {
        this.books.add(livro);
    }

    public void loanBook(Livro livro, User user) {
        livro.emprestar();
        loanedBooksByUser
                .computeIfAbsent(user, u -> new ArrayList<>())
                .add(livro);
        System.out.println("Emprestado");
    }

    public void returnBook(Livro livro, User user) {
        List<Livro> lista = loanedBooksByUser.get(user);

        if (lista != null) {
            lista.remove(livro);

            System.out.println("Devolvido");
        } else {
            System.out.println("O usuário não está com o livro");
        }
    }

    public void filterAvailableBooks() {
        books.stream().filter(Livro::isDisponivel).toList().forEach(livro -> System.out.println(livro.getTitulo() + " - " + livro.getAutor()));
    }

    public void listLoanedBooksByUser() {
        if (loanedBooksByUser.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        for (Map.Entry<User, List<Livro>> entry : loanedBooksByUser.entrySet()) {
            User user = entry.getKey();
            List<Livro> livros = entry.getValue();

            System.out.println("Usuário: " + user.getName());

            for (Livro livro : livros) {
                System.out.println("   - " + livro.getTitulo());
            }
        }
    }

    public void listByTitle() {
        System.out.println(books.stream().map(Livro::getTitulo).toList());
    }

    public void orderAZ() {
        books.stream().sorted(Comparator.comparing(Livro::getTitulo).thenComparing(Livro::getAutor)).forEach(System.out::println);
    }


    public void groupByAuthor() {
        Map<String, List<Livro>> livrosPorAutor = books.stream().filter(Livro::isDisponivel).collect(Collectors.groupingBy(Livro::getAutor));
        livrosPorAutor.forEach((autor, lista) -> {
                    System.out.println("Autor: " + autor);
                    lista.stream().sorted(Comparator.comparing(Livro::getPreco).reversed()).forEach(livro -> System.out.println(" - " + livro.getTitulo()));
                }
        );
    }

    public void stats() {
        stats = books.stream().mapToDouble(Livro::getPreco).summaryStatistics();
        System.out.println("Livro mais caro: " + stats.getMax());
        System.out.println("Livro mais barato: " + stats.getMin());
        System.out.println("Quantidade de livros: " + stats.getCount());
    }

    ;

    public void listNameAndUserId() {
        register.forEach((integer, user) -> System.out.println("Nome: " + user.getName() + ", Matricula: " + integer));
    }

    public Livro getLivroByTitleAndAuthor(String author, String title) {
        return books.stream().filter(x -> x.getTitulo().equals(title) && x.getAutor().equals(author)).findAny().orElse(null);
    }

    public User findUserById(int id) {
        if (register.containsKey(id)) {
            return register.get(id);
        } else {
            return null;
        }
    }


    @Override
    public String toString() {
        return "Biblioteca{" +
                "livro=" + books +
                ", usuario=" + register +
                '}';
    }
}
