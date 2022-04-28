package training;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class apiTests {

    @Test
    public void getCategories(){
        String endpoint = "http://localhost:8888/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
//        var response =
                given().
                        queryParam("id", 2).
                        when().
                        get(endpoint).
                        then().
                        assertThat().statusCode(200).
                        body("id", equalTo("2")).
                        body("name", containsStringIgnoringCase("Back")).
                        body("price", equalTo("299.00")).
                        body("category_id", equalTo("2")).
                        body("category_name", containsString("Wear"));


//        response.log().body();
    }


    @Test
    public void createProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                         
                {
                "name": "Disposable Water",
                "description": "One of the best you'd find in Ogun state",
                "price": 30,
                "category_id": 2
                }
                """;
               var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                         
                {
                "id": 19,
                "name": "Bottle Water",
                "description": "One of the best you'd find in Ekiti state",
                "price": 40,
                "category_id": 4
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                         
                {
                "id": 19            
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Sachet Water New",
                "One of the best you'd find in the city of Ekiti",
                45,
                4
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void createNewProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                         
                {
                "name": "Sweatband",
                "description": "Beautiful knitted sweatband for sale",
                "price": 50,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateNewProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                         
                {
                "id": 24,
                "name": "Sweatband",
                "description": "Beautiful knitted sweatband for sale",
                "price": 60,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void readNewProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php?id=24";
//        String body = """
//
//                {
//                "id": 24,
//                "name": "Sweatband",
//                "description": "Beautiful knitted sweatband for sale",
//                "price": 60,
//                "category_id": 3
//                "category_name":
//                }
//                """;
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteNewProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                         
                {
                "id": 24            
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void getSmallProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read.php";
//        var response =
                given().
                when().
                get(endpoint).
                then().log().headers().
                assertThat().statusCode(200).
                header("Content-Length", equalTo("2048")).
                header("Content-Type", containsString("-8")).
                body("records.size()", greaterThan(21)).
                body("records.id", everyItem(notNullValue())).
                body("records.name", everyItem(notNullValue())).
                body("records.description", everyItem(notNullValue())).
                body("records.price", everyItem(notNullValue())).
                body("records.category_id", everyItem(notNullValue())).
                body("records.category_name", everyItem(notNullValue())).
                body("records.id[0]", equalTo("23")).
                body("records.name[0]", containsString("Water")).
                body("records.id[3]", equalTo("20"));

    }

    @Test
    public void getMultivitaminProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php?id=18";

        var response = given().
                when().
                get(endpoint).
                then().log().
                headers().
                assertThat().statusCode(200).
                header("Content-Length", equalTo("188")).
                header("Content-Type", containsString("json")).
                body("id", equalTo(("18"))).
                body("name", containsString("90")).
                body("description", containsString("daily dose")).
                body("price", equalTo("10.00")).
                body("category_id", greaterThan(("3"))).
                body("category_name", containsString(("Supplements")));
                response.log().body();
    }
}
