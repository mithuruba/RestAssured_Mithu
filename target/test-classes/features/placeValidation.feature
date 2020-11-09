Feature: Add Place to Google Maps

@AddPlace
Scenario Outline: Verify if location is successfully added using AddPlace API
Given required input payload with "<name>", "<language>" and "<address>"
When users calls "AddPlaceAPI" using "POST" request
Then API call is successful with status code
And "status" in response body is "OK"
And "scope" in response body is "APP"
And validate "getPlaceAPI" using place_id generated mapping to "<name>"

Examples:
|name	|language	|address		|
|mithu	|French-IN	|123 royal Ln	|
#|ruba	|English-IN	|456 royal Ln	|


@DeletePlace
Scenario: Validate Delete Place API functionality
Given DeletePlaceAPI payload
When users calls "deletePlaceAPI" using "POST" request
Then API call is successful with status code
And "status" in response body is "OK"
