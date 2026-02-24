package Model;

public class Livro {
    private String titulo;
    private String autor;
    private boolean disponivel;

    public double getPreco() {
        return preco;
    }


    private double preco;

    public Livro(String titulo, String autor, double preco) {
        this.autor = autor;
        this.titulo = titulo;
        this.preco = preco;
        this.disponivel = true;
    }

    public String getAutor() {
        return autor;
    }


    public boolean isDisponivel() {
        return disponivel;
    }


    public String getTitulo() {
        return titulo;
    }


    public void emprestar() {
        this.disponivel = false;
    }

    public void devolver() {
        if (!isDisponivel()) {
            this.disponivel = true;
        }
    }

}
