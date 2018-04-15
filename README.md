<div id="header">

# A Simple Bricks Ordering System

</div>

<div id="content">

<div class="sect1">

## Create an Order

<div class="sectionbody">

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    POST /order?amount=1000 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/x-www-form-urlencoded

    amount=1000

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 37

    {"id":1,"amount":1000,"status":"NEW"}

</div>

</div>

</div>

</div>

<div class="sect1">

## Query an Order

<div class="sectionbody">

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    GET /order?id=1 HTTP/1.1
    Host: localhost:8080

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 37

    {"id":1,"amount":1000,"status":"NEW"}

</div>

</div>

</div>

</div>

<div class="sect1">

## Query All Orders

<div class="sectionbody">

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    GET /orders HTTP/1.1
    Host: localhost:8080

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 115

    [{"id":1,"amount":1000,"status":"NEW"},{"id":2,"amount":2000,"status":"NEW"},{"id":3,"amount":3000,"status":"NEW"}]

</div>

</div>

</div>

</div>

<div class="sect1">

## Cancel an Order

<div class="sectionbody">

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    DELETE /order?id=2 HTTP/1.1
    Host: localhost:8080

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 43

    {"id":2,"amount":2000,"status":"CANCELLED"}

</div>

</div>

</div>

</div>

<div class="sect1">

## Fulfill an Order

<div class="sectionbody">

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    GET /order/fulfill?id=3 HTTP/1.1
    Host: localhost:8080

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 41

    {"id":3,"amount":3000,"status":"SHIPPED"}

</div>

</div>

</div>

</div>

<div class="sect1">

## Modify an Order

<div class="sectionbody">

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    PUT /order?id=1&amount=1111 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/x-www-form-urlencoded

    id=1&amount=1111

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 200 OK
    Content-Type: application/json;charset=UTF-8
    Content-Length: 37

    {"id":1,"amount":1111,"status":"NEW"}

</div>

</div>

<div class="sect2">

### A cancelled order cannot be modified

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    PUT /order?id=2&amount=2222 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/x-www-form-urlencoded

    id=2&amount=2222

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 400 Bad Request

</div>

</div>

</div>

<div class="sect2">

### A fulfilled order can no longer be modified

<div class="listingblock">

<div class="title">request</div>

<div class="content">

    PUT /order?id=3&amount=3333 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/x-www-form-urlencoded

    id=3&amount=3333

</div>

</div>

<div class="listingblock">

<div class="title">response</div>

<div class="content">

    HTTP/1.1 400 Bad Request

</div>

</div>

</div>

</div>

</div>

</div>

<div id="footer">

<div id="footer-text">Last updated 2018-04-15 15:22:19 UTC</div>

</div>
