$(document).ready(function () {

    $("#search-form").submit(function (event) {
        event.preventDefault();

        fire_ajax_submit();
    });


    $("#search-form-book").submit(function (event) {
            event.preventDefault();

            fire_ajax_book_submit()
        });


    $("#search-form-book-titles-that-start-with").submit(function (event) {
                event.preventDefault();

                fire_ajax_book_titles_that_start_with_submit()
            });


    $("#search-form-authors").submit(function (event) {
                        event.preventDefault();

                        fire_ajax_search_authors_submit()
                    });

    $("#search-form-authors-filtered-by-copyrightYear").submit(function (event) {
                    event.preventDefault();

                    fire_ajax_search_authors_filtered_by_copyrightYear_submit()
                });


    $("#search-form-authors-filtered-by-publisher-and-name").submit(function (event) {
                        event.preventDefault();

                        fire_ajax_search_authors_filtered_by_publisher_and_name_submit()
                    });

    $("#create-form-booked-book").submit(function (event) {
                            event.preventDefault();

                            fire_ajax_create_booked_book_submit()
                        });


    $("#search-form-user-wsdl").submit(function (event) {
                event.preventDefault();

                fire_wsdl_user_submit()
            });
});

function fire_ajax_submit() {

    var search = {}
    search["username"] = $("#username").val();
    //search["email"] = $("#email").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}


    function fire_ajax_book_submit() {

        var search = {}
        search["bookTitle"] = $("#bookTitle").val();

        $("#btn-search-book").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/search-book",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-search-book").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-search-book").prop("disabled", false);

            }
        });
    }


  function fire_ajax_book_titles_that_start_with_submit() {

        var search = {}
        search["bookTitlesThatStartWith"] = $("#bookTitlesThatStartWith").val();

        $("#bth-search-book-titles-that-start-with").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/search-book-titles-that-start-with",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#bth-search-book-titles-that-start-with").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#bth-search-book-titles-that-start-with").prop("disabled", false);

            }
        });
    }

function fire_ajax_search_authors_submit() {

        var search = {}
        search["authors"] = $("#authors").val();

        $("#bth-search-authors").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/search-authors",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#bth-search-authors").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#bth-search-authors").prop("disabled", false);

            }
        });
    }

function fire_ajax_search_authors_filtered_by_copyrightYear_submit() {

        var search = {}
        search["authorsFilteredByCopyrightYear"] = $("#authorsFilteredByCopyrightYear").val();

        $("#bth-search-authors-filtered-by-copyrightYear").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/search-authors-filtered-by-copyrightYear",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#bth-search-authors-filtered-by-copyrightYear").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#bth-search-authors-filtered-by-copyrightYear").prop("disabled", false);

            }
        });
    }



function fire_ajax_search_authors_filtered_by_publisher_and_name_submit() {

        var search = {}
        search["authorsFilteredByPublisherAndName"] = $("#authorsFilteredByPublisherAndName").val();

        $("#bth-search-authors-filtered-by-publisher-and-name").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/search-authors-filtered-by-publisher-and-name",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#bth-search-authors-filtered-by-publisher-and-name").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#bth-search-authors-filtered-by-publisher-and-name").prop("disabled", false);

            }
        });
    }


function fire_ajax_create_booked_book_submit() {

        var search = {}
        search["bookedBook"] = $("#bookedBook").val();

        $("#bth-create-booked-book").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/add-booked-book",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#bth-create-booked-book").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#bth-create-booked-book").prop("disabled", false);

            }
        });
    }





function fire_wsdl_user_submit() {

        var search = {}
        search["userTitleWSDL"] = $("#userTitleWSDL").val();
        //search["email"] = $("#email").val();

        $("#btn-search-user-wsdl").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/search-user-wsdl",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-search-user-wsdl").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4><pre>"
                    + e.responseText + "</pre>";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-search-user-wsdl").prop("disabled", false);

            }
        });
    }