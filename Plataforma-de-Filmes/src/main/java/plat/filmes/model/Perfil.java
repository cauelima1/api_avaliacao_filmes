package plat.filmes.model;

public enum Perfil {
    LEITOR("leitor"),
    BASICO("basico"),
    AVANCADO("avancado"),
    MODERADOR("moderador");

    private String role;

    Perfil(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
