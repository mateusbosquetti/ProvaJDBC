public class ClubeLiga {
    private Clube clube;
    private Liga liga;

    public ClubeLiga(Clube clube, Liga liga) {
        this.clube = clube;
        this.liga = liga;
    }

    @Override
    public String toString() {
        return "ClubeLiga{" +
                "clube=" + clube.getNome() +
                ", liga=" + liga.getNome() +
                '}';
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
}
