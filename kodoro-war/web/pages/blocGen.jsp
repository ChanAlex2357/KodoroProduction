<section class="container">
    <div class="row">
        <div>
            <form method="post" action="genbloc">
                <!-- Quantite -->
                <div class="form-group mb-3">
                    <label class="form-label" for="quantite">Quantite</label>
                    <input type="number" name="quantite" id="quantite" class="form-control" value="1000"> 
                </div>
                <!-- Longueur -->
                <div class="form-group">
                    <label class="from-label">Longueur</label>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="Lmin">Min</label>
                            <input type="number" class="form-control" name="Lmin" id="Lmin" placeholder="min" value="5">
                        </div>
                        <div class="col-6 form-group">
                            <label for="Lmax">Max</label>
                            <input type="number" class="form-control" name="Lmax" id="Lmax" placeholder="max" value="7">
                        </div>
                    </div>
                </div>
                <!-- Largeur -->
                <div class="form-group">
                    <label class="from-label">Largeur</label>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="lmin">Min</label>
                            <input type="number" class="form-control" name="lmin" id="lmin" placeholder="min" value="20">
                        </div>
                        <div class="col-6 form-group">
                            <label for="lmax">Max</label>
                            <input type="number" class="form-control" name="lmax" id="lmax" placeholder="max" value="25">
                        </div>
                    </div>
                </div>
                <!-- Epaisseur -->
                <div class="form-group">
                    <label class="from-label">Epaisseur</label>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="lmin">Min</label>
                            <input type="number" class="form-control" name="emin" id="emin" placeholder="min" value="10">
                        </div>
                        <div class="col-6 form-group">
                            <label for="emax">Max</label>
                            <input type="number" class="form-control" name="emax" id="emax" placeholder="max" value="15">
                        </div>
                    </div>
                </div>
                <!-- Annee -->
                <div class="form-group">
                    <label class="from-label">Annee de production</label>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="amin">Min</label>
                            <input type="number" class="form-control" name="amin" id="amin" placeholder="min" value="2022">
                        </div>
                        <div class="col-6 form-group">
                            <label for="amax">Max</label>
                            <input type="number" class="form-control" name="amax" id="amax" placeholder="max" value="2024">
                        </div>
                    </div>
                </div>
                <!-- Marge du prix de revient -->
                <div class="form-group">
                    <label for="marge" class="from-label">Marge sur prix de revient</label>
                    <input type="number" class="form-control" name="marge" id="marge" value="10">
                </div>
                <div>
                    <input type="submit" class="btn btn-primary" value="Generer">
                </div>
            </form>
        </div>
    </div>
</section>