import Model.Livro;
import Model.User;
import Service.Biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        List<User> u = new ArrayList<>();
        List<Livro> l = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        boolean running = true;
        int userCounter = 0, bookCounter = 0;
        int escolha1;
        int escolha2;
        int idMat = 0;
        while (running) {
            System.out.println("Cadastrar/Emprestar : 1");
            System.out.println("Listar: 2");
            System.out.println("Encerrar programa: 0");
            escolha1 = s.nextInt();
            switch (escolha1) {
                case 1:
                    System.out.println("Cadastrar usuário: 1");
                    System.out.println("Cadastrar livro: 2");
                    System.out.println("Emprestar livro: 3");
                    System.out.println("Devolver livro: 4");
                    escolha2 = s.nextInt();
                    int matricula;
                    String title;
                    String author;
                    Livro livroParaEmprestar;
                    switch (escolha2) {
                        case 1:
                            System.out.println("Digite o nome");
                            int mat = idMat;
                            s.nextLine();
                            String nome = s.nextLine();
                            u.add(new User(mat, nome));
                            biblioteca.registerUser(u.get(userCounter));
                            userCounter++;
                            idMat++;
                            break;
                        case 2:
                            System.out.println("DIgite o nome do autor");
                            s.nextLine();
                            author = s.nextLine();
                            System.out.println("Digite o titulo do livro");
                            title = s.nextLine();
                            System.out.println("Digite o preço");
                            double preco = s.nextDouble();
                            l.add(new Livro(title, author, preco));
                            biblioteca.registerBook(l.get(bookCounter));
                            bookCounter++;
                            break;
                        case 3:
                            System.out.println("Digite a matricula do usuario");
                            s.nextLine();
                            matricula = s.nextInt();
                            s.nextLine();
                            System.out.println("Digite o nome do autor");
                            author = s.nextLine();
                            System.out.println("Digite o titulo do livro");
                            title = s.nextLine();

                            livroParaEmprestar = biblioteca.getLivroByTitleAndAuthor(author, title);
                            System.out.println(livroParaEmprestar.getTitulo() + " " + livroParaEmprestar.getAutor());

                            if (livroParaEmprestar == null) {
                                System.out.println("Livro não encontrado");
                            } else if (!livroParaEmprestar.isDisponivel()) {
                                System.out.println("Livro indisponivel");
                            } else if (biblioteca.findUserById(matricula) == null) {
                                System.out.println("Usuario não encontrado");
                            } else {
                                biblioteca.loanBook(biblioteca.getLivroByTitleAndAuthor(author, title), biblioteca.findUserById(matricula));
                            }
                            break;
                        case 4:
                            System.out.println("Digite a matricula do usuario");
                            s.nextLine();
                            matricula = s.nextInt();
                            s.nextLine();
                            System.out.println("Digite o nome do autor");
                            author = s.nextLine();
                            System.out.println("Digite o titulo do livro");
                            title = s.nextLine();

                            livroParaEmprestar = biblioteca.getLivroByTitleAndAuthor(title, author);

                            if (livroParaEmprestar == null) {
                                System.out.println("Model.Livro não encontrado");
                            } else if (!livroParaEmprestar.isDisponivel()) {
                                System.out.println("Model.Livro indisponivel");
                            } else if (biblioteca.findUserById(matricula) == null) {
                                System.out.println("Usuario não encontrado");
                            } else {
                                biblioteca.returnBook(biblioteca.getLivroByTitleAndAuthor(title, author), biblioteca.findUserById(matricula));
                            }
                            break;

                        default:
                            System.out.println("Opção inexistente!");

                    }
                    break;
                case 2:
                    escolha2 = s.nextInt();
                    System.out.println("Filtrar livros disponíveis: 1");
                    System.out.println("Listar livros por titulo: 2");
                    System.out.println("Listar livros em ordem: 3");
                    System.out.println("Listar livros emprestados por usuário: 4");
                    System.out.println("LIstar livros por autor: 5");
                    System.out.println("Mostrar estatísticas da biblioteca: 6");
                    System.out.println("Listar usuários e matrícula: 7");
                    switch (escolha2) {
                        case 1:
                            biblioteca.filterAvailableBooks();
                            break;
                        case 2:
                            biblioteca.listByTitle();
                            break;
                        case 3:
                            biblioteca.orderAZ();
                            break;
                        case 4:
                            biblioteca.listLoanedBooksByUser();
                            break;
                        case 5:
                            biblioteca.groupByAuthor();
                            break;
                        case 6:
                            biblioteca.stats();
                            break;
                        case 7:
                            biblioteca.listNameAndUserId();
                            break;
                        default:
                            System.out.println("Opção inexistente!");
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opção inexistente!");

            }
        }


    }
}
