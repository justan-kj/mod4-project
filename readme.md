


# Travel App


## Table of Contents

  - [Changelog](#changelog)
  - [1. Login](#1-login)
  - [2. Get itineraries](#2-get-itineraries)
  - [3. Delete itinerary](#3-delete-itinerary)
  - [4. Get user itinerary](#4-get-user-itinerary)
  - [5. Add destination](#5-add-destination)
  - [6. Delete destination](#6-delete-destination)
  - [7. Update destination](#7-update-destination)
  - [Glossary](#glossary)

## Changelog
<table>
  <tr>
   <td><strong>Version</strong>
   </td>
   <td><strong>Date</strong>
   </td>
   <td><strong>Author</strong>
   </td>
   <td><strong>Description</strong>
   </td>
  </tr>
  <tr>
   <td>1.0
   </td>
   <td>13-Mar-2023
   </td>
   <td>Justin Tan
   </td>
   <td>Initial draft
   </td>
  </tr>
</table>


## 1. Login

Authenticate the user with the system and obtain the auth_token


### Request


<table>
  <tr>
   <td><strong>Method</strong>
   </td>
   <td><strong>URL            </strong>
   </td>
  </tr>
  <tr>
   <td><strong><code>POST</code></strong>
   </td>
   <td><code>api/login/</code>
   </td>
  </tr>
</table>



<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Params</strong>
   </td>
   <td><strong>Values</strong>
   </td>
  </tr>
    <tr>
        <td><code>HEAD</code></td>
        <td><code>api_key</code></td>
        <td><code>string</code></td>
    </tr>
    <tr>
    <td><code>POST</code></td>
    <td><code>username</code></td>
    <td><code>string</code></td>
    </tr>
    <tr>
    <td><code>POST</code>
    <td><code>password</code>
    <td><code>string</code>
    </tr>
  
</table>



**api_key** 

Must be sent with all client requests. The api_key helps the server to validate the request source.


### Response


<table>
  <tr>
   <td><strong>Status</strong>
   </td>
   <td><strong>Response</strong>
   </td>
  </tr>
  <tr>
   <td><code>200</code>
   </td>
   <td><code>{</code>
<p>
<code>    "auth_key": &lt;auth_key></code>
<p>
<code>}</code>
<p>
<code>auth_key (<strong>string</strong>)</code> - all further API calls must have this key in header
   </td>
  </tr>
  <tr>
   <td><code>403</code>
   </td>
   <td><code>{"error":"API key is missing."}</code>
   </td>
  </tr>
  <tr>
   <td><code>400</code>
   </td>
   <td><code>{"error":"Please provide username."}</code>
   </td>
  </tr>
  <tr>
   <td><code>400</code>
   </td>
   <td><code>{"error":"Please provide password."}</code>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Invalid API key."}</code>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Incorrect username or password."}</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>{"error":"Something went wrong. Please try again later."}</code>
   </td>
  </tr>
</table>



## 2. Get itineraries

Get all itineraries.


### Request


<table>
  <tr>
   <td><strong>Method</strong>
   </td>
   <td><strong>URL            </strong>
   </td>
  </tr>
  <tr>
   <td><strong><code>GET</code></strong>
   </td>
   <td><code>itineraries</code>
   </td>
  </tr>
</table>



<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Params</strong>
   </td>
   <td><strong>Values</strong>
   </td>
  </tr>
  <tr>
   <td><code>HEAD</code>
   </td>
   <td><code>auth_key</code>
   </td>
   <td><code>string</code>
   </td>
  </tr>
</table>



**auth_key**

The ` auth_key ` that was given in response to `/api/login`


### Response


<table>
  <tr>
   <td><strong>Status</strong>
   </td>
   <td><strong>Response</strong>
   </td>
  </tr>
  <tr>
   <td><code>200</code>
   </td>
   <td><strong>Response will be an object containing the list of itineraries (array). </strong>
<p>
<strong>Each item in the array has the following structure.</strong>
<p>
<code>{</code>
<p>
<code>    "id": 1,</code>
<p>
<code>    "user_id": 10,</code>
<p>
<code>    "destination_city": "Perth",</code>
<p>
<code>    "destination_country": "Australia",</code>
<p>
<code>    "destination_start_date": 2023-06-01,</code>
<p>
<code>    "destination_end_date": 2023-06-11,</code>
<p>
<code>    "budget": 3.50,    </code>
<p>
<code>    "transport_id": 1,</code>
<p>
<code>    "accomodation_id": 3</code>
<p>
<code>}</code>
<p>
<strong>An example response is:-</strong>
<p>
<code>{</code>
<p>
<code>    "itineraries": [</code>
<p>
<code>    {</code>
<p>
<code>        "id": 1,</code>
<p>
<code>        "user_id": 10,</code>
<p>
<code>        "destination_city": "Perth",</code>
<p>
<code>        "destination_country": "Australia",</code>
<p>
<code>        "destination_start_date": 2023-06-01,</code>
<p>
<code>        "destination_end_date": 2023-06-11,</code>
<p>
<code>        "budget": 3.50,    </code>
<p>
<code>        "transport_id": 1,</code>
<p>
<code>        "accomodation_id": 3</code>
<p>
<code>    }</code>
<p>
<code>    ]</code>
<p>
<code>}</code>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Invalid API key."}</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>{"error":"Something went wrong. Please try again later."}</code>
   </td>
  </tr>
</table>



## 3. Delete itinerary

Delete all itinerary items for the user id.


### Request


<table>
  <tr>
   <td><strong>Method</strong>
   </td>
   <td><strong>URL            </strong>
   </td>
  </tr>
  <tr>
   <td><strong><code>DELETE</code></strong>
   </td>
   <td><code>itinerary/{userId}</code>
   </td>
  </tr>
</table>



<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Params</strong>
   </td>
   <td><strong>Values</strong>
   </td>
  </tr>
  <tr>
   <td><code>HEAD</code>
   </td>
   <td><code>auth_key</code>
   </td>
   <td><code>String</code>
   </td>
  </tr>
</table>




**auth_key**

The ` auth_key ` that was given in response to `/api/login`


### Response

<table>
  <tr>
   <td><strong>Status</strong>
   </td>
   <td><strong>Response</strong>
   </td>
  </tr>
  <tr>
   <td><code>204</code>
   </td>
   <td><strong>Empty body</strong>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Invalid Auth key."}</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>{"error":"Something went wrong. Please try again later."}</code>
   </td>
  </tr>
</table>



## 4. Get user itinerary

Gets the itinerary for the specified userId.


### Request


<table>
  <tr>
   <td><strong>Method</strong>
   </td>
   <td><strong>URL            </strong>
   </td>
  </tr>
  <tr>
   <td><strong><code>GET</code></strong>
   </td>
   <td><code>itineraries/{userId}</code>
   </td>
  </tr>
</table>



<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Params</strong>
   </td>
   <td><strong>Values</strong>
   </td>
  </tr>
  <tr>
   <td><code>HEAD</code>
   </td>
   <td><code>auth_key</code>
   </td>
   <td><code>string</code>
   </td>
  </tr>
</table>



**auth_key**

The ` auth_key ` that was given in response to `/api/login`


### Response {#response}


<table>
  <tr>
   <td><strong>Status</strong>
   </td>
   <td><strong>Response</strong>
   </td>
  </tr>
  <tr>
   <td><code>200</code>
   </td>
   <td><strong>Response will be an object containing the itinerary with user_id = userId. </strong>
<p>
<strong>An example response is:-</strong>
<p>
<code>    {</code>
<p>
<code>        "id": 1,</code>
<p>
<code>        "user_id": 10,</code>
<p>
<code>        "destination_city": "Perth",</code>
<p>
<code>        "destination_country": "Australia",</code>
<p>
<code>        "destination_start_date": 01/06/2023,</code>
<p>
<code>        "destination_end_date": 08/06/2023,</code>
<p>
<code>        "budget": 3.50,    </code>
<p>
<code>        "transport_id": 1,</code>
<p>
<code>        "accomodation_id": 3</code>
<p>
<code>    }</code>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Invalid API key."}</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>{"error":"Something went wrong. Please try again later."}</code>
   </td>
  </tr>
</table>



## 5. Add destination

Add an item to the itinerary for the specified userId.


### Request


<table>
  <tr>
   <td><strong>Method</strong>
   </td>
   <td><strong>URL            </strong>
   </td>
  </tr>
  <tr>
   <td><strong><code>POST</code></strong>
   </td>
   <td><code>itineraries/{userId}</code>
   </td>
  </tr>
</table>



<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Params</strong>
   </td>
   <td><strong>Values</strong>
   </td>
  </tr>
  <tr>
   <td><code>HEAD</code>
<p>
<code>POST</code>
   </td>
   <td><code>auth_key</code>
<p>
<code>destinationCity</code>
   </td>
   <td><code>string</code>
<p>
<code>string</code>
   </td>
  </tr>
</table>




**auth_key**


The ` auth_key ` that was given in response to `/api/login`



**destinationCity**


The destination city to be deleted.


### Body


<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Content</strong>
   </td>
  </tr>
  <tr>
   <td><code>application/json</code>
   </td>
   <td><strong>Content will be an object containing the new itinerary item, sans userId and id.</strong>
<p>
<code>Example content:-</code>
<p>
<code>    {</code>
<p>
<code>        "destination_city": "Perth",</code>
<p>
<code>        "destination_country": "Australia",</code>
<p>
<code>        "destination_start_date": 01/06/2023,</code>
<p>
<code>        "destination_end_date": 08/06/2023,</code>
<p>
<code>        "budget": 3.50,    </code>
<p>
<code>        "transport_id": 1,</code>
<p>
<code>        "accomodation_id": 3</code>
<p>
<code>    }</code>
   </td>
  </tr>
</table>



### Response


<table>
  <tr>
   <td><strong>Status</strong>
   </td>
   <td><strong>Response</strong>
   </td>
  </tr>
  <tr>
   <td><code>200</code>
   </td>
   <td><strong>The ID of the created itinerary.</strong>
<p>
<code>Example response:-</code>
<p>
<code>{"itinerary":10}</code>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Invalid API key."}</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>{"error":"Something went wrong. Please try again later."}</code>
   </td>
  </tr>
</table>



## 6. Delete destination

Delete itinerary record with the `itineraryId` for the specified userId.


### Request


<table>
  <tr>
   <td><strong>Method</strong>
   </td>
   <td><strong>URL            </strong>
   </td>
  </tr>
  <tr>
   <td><strong><code>DELETE</code></strong>
   </td>
   <td><code>itineraries/{userId}/{itineraryId}</code>
   </td>
  </tr>
</table>



<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Params</strong>
   </td>
   <td><strong>Values</strong>
   </td>
  </tr>
  <tr>
   <td><code>HEAD</code>
   </td>
   <td><code>auth_key</code>
   </td>
   <td><code>string</code>
   </td>
  </tr>
</table>




**auth_key**

The ` auth_key ` that was given in response to `/api/login`



**destinationCity**


The destination city to be deleted.


### Response


<table>
  <tr>
   <td><strong>Status</strong>
   </td>
   <td><strong>Response</strong>
   </td>
  </tr>
  <tr>
   <td><code>204</code>
   </td>
   <td><strong>Empty Body</strong>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Invalid API key."}</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>{"error":"Something went wrong. Please try again later."}</code>
   </td>
  </tr>
</table>



## 7. Update destination

Update itinerary records with the `itineraryId ` for the specified userId.


### Request {#request}


<table>
  <tr>
   <td><strong>Method</strong>
   </td>
   <td><strong>URL            </strong>
   </td>
  </tr>
  <tr>
   <td><strong><code>PUT</code></strong>
   </td>
   <td><code>itineraries/{userId}/{itineraryId}</code>
   </td>
  </tr>
</table>



<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Params</strong>
   </td>
   <td><strong>Values</strong>
   </td>
  </tr>
  <tr>
   <td><code>HEAD</code>
   </td>
   <td><code>auth_key</code>
   </td>
   <td><code>string</code>
   </td>
  </tr>
</table>



**auth_key**

The ` auth_key ` that was given in response to `/api/login`


### Body


<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>Content</strong>
   </td>
  </tr>
  <tr>
   <td><code>application/json</code>
   </td>
   <td><strong>Content will be an object containing the updated itinerary item, sans userId and id.</strong>
<p>
<code>Example content:-</code>
<p>
<code>    {</code>
<p>
<code>        "destination_city": "Perth",</code>
<p>
<code>        "destination_country": "Australia",</code>
<p>
<code>        "destination_start_date": 01/06/2023,</code>
<p>
<code>        "destination_end_date": 08/06/2023,</code>
<p>
<code>        "budget": 3.50,    </code>
<p>
<code>        "transport_id": 1,</code>
<p>
<code>        "accomodation_id": 3</code>
<p>
<code>    }</code>
   </td>
  </tr>
</table>



### Response


<table>
  <tr>
   <td><strong>Status</strong>
   </td>
   <td><strong>Response</strong>
   </td>
  </tr>
  <tr>
   <td><code>200</code>
   </td>
   <td><strong>The ID of the updated itinerary.</strong>
<p>
<code>Example response:-</code>
<p>
<code>{"itinerary":10}</code>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>{"error":"Invalid API key."}</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>{"error":"Something went wrong. Please try again later."}</code>
   </td>
  </tr>
</table>






## Glossary



### Conventions



* **Client** - Client application.
* **Status** - HTTP status code of response.
* All the possible responses are listed under ‘Responses’ for each method. Only one of them is issued per request server.
* All response are in JSON format.
* All request parameters are mandatory unless explicitly marked as `[optional]`
* The type of values accepted for a _request_ parameter are shown the the values column like this <code>[<strong>10</strong>|&lt;any number>]</code> .The <code>|</code> symbol means <em>OR</em>. If the parameter is <code>[optional]</code>, the default value is shown in blue bold text, as <strong><code>10</code></strong> is written in <code>[<strong>10</strong>|&lt;any number>]</code>.


### Status Codes {#status-codes}

All status codes are standard HTTP status codes. The below ones are used in this API.

`2XX - `Success of some kind

`4XX - `Error occurred in client’s part

`5XX - `Error occurred in server’s part


<table>
  <tr>
   <td><strong>Status Code</strong>
   </td>
   <td><strong>Description</strong>
   </td>
  </tr>
  <tr>
   <td><code>200</code>
   </td>
   <td><code>OK</code>
   </td>
  </tr>
  <tr>
   <td><code>201</code>
   </td>
   <td><code>Created</code>
   </td>
  </tr>
  <tr>
   <td><code>202</code>
   </td>
   <td><code>Accepted (Request accepted, and queued for execution)</code>
   </td>
  </tr>
  <tr>
   <td><code>400</code>
   </td>
   <td><code>Bad request</code>
   </td>
  </tr>
  <tr>
   <td><code>401</code>
   </td>
   <td><code>Authentication failure</code>
   </td>
  </tr>
  <tr>
   <td><code>403</code>
   </td>
   <td><code>Forbidden</code>
   </td>
  </tr>
  <tr>
   <td><code>404</code>
   </td>
   <td><code>Resource not found</code>
   </td>
  </tr>
  <tr>
   <td><code>405</code>
   </td>
   <td><code>Method Not Allowed</code>
   </td>
  </tr>
  <tr>
   <td><code>409</code>
   </td>
   <td><code>Conflict</code>
   </td>
  </tr>
  <tr>
   <td><code>412</code>
   </td>
   <td><code>Precondition Failed</code>
   </td>
  </tr>
  <tr>
   <td><code>413</code>
   </td>
   <td><code>Request Entity Too Large</code>
   </td>
  </tr>
  <tr>
   <td><code>500</code>
   </td>
   <td><code>Internal Server Error</code>
   </td>
  </tr>
  <tr>
   <td><code>501</code>
   </td>
   <td><code>Not Implemented</code>
   </td>
  </tr>
  <tr>
   <td><code>503</code>
   </td>
   <td><code>Service Unavailable</code>
   </td>
  </tr>
</table>

