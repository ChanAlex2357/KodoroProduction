<section class="container">
    <div class="row">
        <div>
            <form action="">
                <!-- Quantite -->
                <div class="form-group mb-3">
                    <label class="form-label" for="quantite">Quantite</label>
                    <input type="number" name="quantite" id="quantite" class="form-control">
                </div>
                <!-- Longueur -->
                <div class="form-group">
                    <label class="from-label">Longueur</label>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="Lmin">Min</label>
                            <input type="number" name="Lmin" id="Lmin" placeholder="min">
                        </div>
                        <div class="col-6 form-group">
                            <label for="Lmax">Max</label>
                            <input type="number" name="Lmax" id="Lmax" placeholder="max">
                        </div>
                    </div>
                </div>
                <!-- Largeur -->
                <div class="form-group">
                    <label class="from-label">Largeur</label>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="lmin">Min</label>
                            <input type="number" name="lmin" id="lmin" placeholder="min">
                        </div>
                        <div class="col-6 form-group">
                            <label for="lmax">Max</label>
                            <input type="number" name="lmax" id="lmax" placeholder="max">
                        </div>
                    </div>
                </div>
                <!-- Annee -->
                <div class="form-group">
                    <label class="from-label">Annee de production</label>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="lmin">Min</label>
                            <input type="number" name="lmin" id="lmin" placeholder="min">
                        </div>
                        <div class="col-6 form-group">
                            <label for="lmax">Max</label>
                            <input type="number" name="lmax" id="lmax" placeholder="max">
                        </div>
                    </div>
                </div>
                <!-- Marge du prix de revient -->
                <div class="form-group">
                    <label for="marge" class="from-label">Marge sur prix de revient</label>
                    <input type="number" name="marge" id="marge">
                </div>
                <div>
                    <input type="submit" class="btn btn-primary" value="Generer">
                </div>
            </form>
        </div>
    </div>
</section>