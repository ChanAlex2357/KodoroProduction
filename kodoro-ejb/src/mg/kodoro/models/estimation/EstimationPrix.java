package mg.kodoro.models.estimation;

public class EstimationPrix {
    double prixDeRevientTotal;
    double beneficeTheorique;
    double chiffreAffaireTheorique;
    public double getPrixDeRevientTotal() {
        return prixDeRevientTotal;
    }
    public void setPrixDeRevientTotal(double prixDeRevientTotal) {
        this.prixDeRevientTotal = prixDeRevientTotal;
    }
    public double getBeneficeTheorique() {
        if (beneficeTheorique == 0 ) {
            setBeneficeTheorique();
        }
        return beneficeTheorique;
    }
    public void setBeneficeTheorique(double beneficeTheorique) {
        System.out.println("BENEF : "+beneficeTheorique);
        this.beneficeTheorique = beneficeTheorique;
    }
    public double getChiffreAffaireTheorique() {
        return chiffreAffaireTheorique;
    }
    public void setChiffreAffaireTheorique(double chiffreAffaireTheorique) {
        this.chiffreAffaireTheorique = chiffreAffaireTheorique;
    }
    public void setBeneficeTheorique() {
        setBeneficeTheorique( this.getChiffreAffaireTheorique() - this.getPrixDeRevientTotal());
    }
}
