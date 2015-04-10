// FONCTIONS
function placerScrumzone() {
    var widthZone = ($(window).width() - (5 * 15) - (4 * 2)) / 4;
    var heightZone = $(window).height() - 107;

    $('.scrumzone').width(widthZone);
    $('.scrumzone').height(heightZone);

    $('.scrumzone-us').css('left', '15px');
    $('.scrumzone-todo').css('left', (widthZone + 17) * 1 + 15 + 'px');
    $('.scrumzone-wip').css('left', (widthZone + 17) * 2 + 15 + 'px');
    $('.scrumzone-done').css('right', '15px');
}

function afficherScrumzone() {
    $('.scrumzone').fadeIn('slow');
}

function afficherActions() {
    $('.menu-gauche').fadeIn('slow');
}


// LISTENERS
$(window).resize(function () {
    placerScrumzone();
});

$('a.list-group-item').on('click', function () {
    var menu = $(this).attr('data-menu');

    $('a.list-group-item.active').removeClass('active');
    $('a.list-group-item[data-menu=' + menu + ']').addClass('active');

    $('.menu-content').css('visibility', 'hidden');
    $('.menu-content[data-menu=' + menu + ']').css('visibility', 'visible');
});

$('#projet-list .item').on('click', function () {

    // TODO Charger les données du projet en question
    afficherActions();
    afficherScrumzone();
});


// AJOUT D'UN PROJET
$('#projet-add').on('click', function () {

    $.ajax({
        method: "POST",
        url: "rest/addProject",
        data: {name: document.getElementById('projet-name').value, dod: document.getElementById('projet-dod').value}
    }).done(function () {
        console.log("[SUCCESS] Ajout d'un projet.");
        // Clear form
        document.getElementById('projet-name').value = "";
        document.getElementById('projet-dod').value = "";
    }).fail(function () {
        console.log("[ERROR] Ajout d'un projet.");
    });

});

// AJOUT D'UNE US
$('#us-add').on('click', function () {

    $.ajax({
        method: "POST",
        url: "rest/addUserStory",
        data: {
            name: document.getElementById('us-name').value,
            te: document.getElementById('us-te').value,
            bv: document.getElementById('us-bv').value
        }
    }).done(function () {
        console.log("[SUCCESS] Ajout d'une user-story.");
        // Clear form
        document.getElementById('us-name').value = "";
        document.getElementById('us-te').value = "";
        document.getElementById('us-bv').value = "";
    }).fail(function () {
        console.log("[SUCCESS] Ajout d'une user-story.");
    });

});


// MAIN
$(function () {
    placerScrumzone();
    $('.menu a[data-menu=projets]').click();
    console.log("ready!");

    var toto = $.ajax({
        method: "GET",
        url: "rest/getProjects"
    }).done(function (data) {
        console.log("[SUCCESS] Récupération des projets.");
        if (console && console.log) {
            console.log("Sample of data:", data.slice(0, 100));
        }

    }).fail(function () {
        console.log("[ERROR] Récupération des projets.");
    });


});