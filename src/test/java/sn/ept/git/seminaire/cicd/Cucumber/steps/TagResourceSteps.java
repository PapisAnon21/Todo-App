package sn.ept.git.seminaire.cicd.Cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TagVM;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.fail;






public class TagResourceSteps {


    private ResponseEntity<TagDTO> response;
    private ResponseEntity<String> responseString;


    private ResponseEntity<Map<String, List<TagDTO>>> responseList;
    private List<TagDTO> tags;
    private UUID tagId;
    private TagDTO retrievedTag;

    private List<TagDTO> retrievedTags;




    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost:8080/cicd/api/tags";
    private String tagName;
    private Map<String, Object> tagListResponse;

    @Given("the user is on the TagResource endpoint")
    public void the_user_is_on_the_TagResource_endpoint() {
        // Configuration initiale
    }

    @When("the user adds a new tag")
    public void the_user_adds_a_new_tag() {
        tagName = "Nom_Tag-" + System.currentTimeMillis();

        // Appel à l'API pour créer un nouveau tag
        TagVM tagVM = new TagVM();
        tagVM.setName(tagName);
        tagVM.setDescription("Description du nouveau tag");

        // Configurer les en-têtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Envoyer la requête POST pour créer le nouveau tag
        // Stocker la réponse pour une vérification ultérieure dans la méthode Then
        this.response = restTemplate.postForEntity(baseUrl, tagVM, TagDTO.class);;

        tagId = response.getBody().getId();
    }

    @Then("the response should contain the newly created tag")
    public void the_response_should_contain_the_newly_created_tag() {
        // Vérification que la réponse de l'API contient le tag nouvellement créé
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        TagDTO createdTag = response.getBody();
        assertNotNull(createdTag);
        assertNotNull(createdTag.getId());
    }

    @When("the user deletes the newly created tag")
    public void the_user_deletes_the_newly_created_tag() {
        // Obtenir l'ID du tag nouvellement créé depuis la réponse précédente
        TagDTO createdTag = response.getBody();
        assertNotNull(createdTag);
        tagId = createdTag.getId();

        // Envoyer la requête DELETE pour supprimer le tag
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                baseUrl + "/" + tagId,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    }

    @Then("the tag should be deleted from the system")
    public void the_tag_should_be_deleted_from_the_system() {
        // Vérifier que la réponse de la requête GET renvoie une erreur 404 (Not Found)
        try {
            restTemplate.getForEntity(baseUrl + "/" + tagId, TagDTO.class);
            fail("Expected HttpClientErrorException to be thrown");
        } catch (HttpClientErrorException ex) {
            // Vérifiez que l'erreur est bien une erreur 404 (Not Found)
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }

    @When("the user requests a tag by its ID")
    public void the_user_requests_a_tag_by_its_ID() {
        the_user_adds_a_new_tag(); // Créer un nouveau tag pour effectuer la requête GET

        // Utiliser l'ID du tag créé précédemment pour effectuer une requête GET pour ce tag
        retrievedTag = restTemplate.getForObject(baseUrl + "/" + tagId, TagDTO.class);
        assertNotNull(retrievedTag);
    }

    @Then("the response should contain the tag with the specified ID")
    public void the_response_should_contain_the_tag_with_the_specified_ID() {
        // Vérifiez que le tag récupéré correspond à celui que vous avez créé
        assertEquals(tagId, retrievedTag.getId());

    }


    @When("the user updates the tag")
    public void the_user_updates_the_tag() {
        // Préparez une mise à jour du tag
        TagVM updatedTag = new TagVM();
        updatedTag.setName("Nouveau nom du Tag" + System.currentTimeMillis());
        updatedTag.setDescription("Nouvelle description du tag");

        // Configurer les en-têtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Envoyer la requête PUT pour mettre à jour le tag
        ResponseEntity<TagDTO> updatedResponse = restTemplate.exchange(
                baseUrl + "/" + tagId,
                HttpMethod.PUT,
                new HttpEntity<>(updatedTag, headers),
                TagDTO.class
        );

        // Stocker la réponse pour une vérification ultérieure dans la méthode Then
        this.response = updatedResponse;
    }

    @Then("the response should contain the updated tag")
    public void the_response_should_contain_the_updated_tag() {
        // Vérification que la réponse de l'API contient le tag mis à jour
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        TagDTO updatedTag = response.getBody();
        assertNotNull(updatedTag);

    }


    @When("the user requests all tags")
    public void the_user_requests_all_tags() {
        // Envoyer une requête GET pour récupérer tous les tags
        responseString = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
    }

    @Then("the response should have a status code of 200 OK")
    public void the_response_should_contain_a_list_of_tags() {
        // Vérifier que la réponse a un code de statut 200 OK
        assertEquals(HttpStatus.OK, responseString.getStatusCode());
    }

    @Then("the response should contain a non-empty list of tags")
    public void the_response_should_contain_non_empty_list_of_tags() {
        assertFalse(responseString.getBody().isEmpty());
    }


    private HttpStatus httpStatus;
    @When("the user tries to create a new tag with invalid data")
    public void the_user_tries_to_create_a_new_tag_with_invalid_data() {
        // Tentative de création d'un nouveau tag avec un nom vide
        TagVM tagVM = new TagVM();
        tagVM.setName(""); // Nom vide comme exemple de données non valides
        tagVM.setDescription("Description du nouveau tag");

        // Configurer les en-têtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Envoyer la requête POST pour créer le nouveau tag
        try {
            this.response = restTemplate.postForEntity(baseUrl, tagVM, TagDTO.class);
            this.httpStatus = response.getStatusCode();
        } catch (HttpClientErrorException e) {
            this.httpStatus = e.getStatusCode();
        }

    }

    @Then("the response should indicate a validation error")
    public void the_response_should_indicate_a_validation_error() {
        // Vérification que la réponse de l'API indique une erreur de validation
        assertEquals(HttpStatus.BAD_REQUEST, httpStatus);
        assertNull(response);

    }


    private HttpStatus responseStatus;
    private String errorMessage;
    @When("the user adds a new tag with the name {string}")
    public void the_user_adds_a_new_tag_with_an_existing_name(String tagName) {
        // Créer un tag avec le nom existant
        TagVM tagVM = new TagVM();
        tagVM.setName(tagName);
        tagVM.setDescription("Description du nouveau tag");

        // Configurer les en-têtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            // Envoyer la requête POST pour créer le nouveau tag
            restTemplate.postForEntity(baseUrl, tagVM, TagDTO.class);
        } catch (HttpClientErrorException e) {
            // Capturer la réponse de l'API en cas d'erreur (409 - Conflict)
            responseStatus = e.getStatusCode();
            errorMessage = e.getResponseBodyAsString();
        }
    }

    @Then("the response should contain a conflict status")
    public void the_response_should_contain_a_conflict_status() {
        assertEquals(HttpStatus.CONFLICT, responseStatus);
    }

    @And("the response should contain an error message")
    public void the_response_should_contain_an_error_message() {
        assertNotNull(errorMessage);
        // Ajoutez ici vos vérifications pour le contenu du message d'erreur retourné par l'API
    }



}
