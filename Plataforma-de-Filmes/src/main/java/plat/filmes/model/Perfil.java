package plat.filmes.model;


public enum Perfil {
    LEITOR("LEITOR"),
    BASICO("BASICO"),
    AVANCADO("AVANCADO"),
    MODERADOR("MODERADOR");

    private String role;

    Perfil(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}
