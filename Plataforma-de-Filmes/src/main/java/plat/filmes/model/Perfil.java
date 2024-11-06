package plat.filmes.model;

public enum Perfil {
    LEITOR("Leitor"),
    BASICO("Basico"),
    AVANCADO("Avancado"),
    MODERADOR("Moderador");

    private String role;

    Perfil(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
