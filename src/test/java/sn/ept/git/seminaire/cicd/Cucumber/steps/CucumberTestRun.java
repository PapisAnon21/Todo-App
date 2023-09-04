package sn.ept.git.seminaire.cicd.Cucumber.steps;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\features", // Chemin vers vos fichiers .feature
        glue = "cucumber.steps" // Chemin vers  classes de définition de pas
)
public class CucumberTestRun {
    // Cette classe ne nécessite pas de méthodes, elle sera utilisée pour exécuter les scénarios Cucumber.
}