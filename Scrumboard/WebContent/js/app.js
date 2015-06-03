// FONCTIONS
function placerScrumzone() {
    var widthZone = ($(window).width() - (5 * 15) - (4 * 2)) / 4;
    var heightZone = $(window).height() - 242;

    $('.scrumzone').width(widthZone);
    $('.scrumzone').height(heightZone);

    $('.scrumzone-planning').css({'top': '90px', 'left': '15px', 'width': $(window).width()-30+'px', 'height': '120px'});

    $('.scrumzone-us').css('left', '15px');
    $('.scrumzone-todo').css('left', (widthZone + 17) * 1 + 15 + 'px');
    $('.scrumzone-wip').css('left', (widthZone + 17) * 2 + 15 + 'px');
    $('.scrumzone-done').css('right', '15px');
}

function afficherScrumzone() {
    $('.scrumzone').fadeIn('slow');
}

function afficherActions() {
    $('.menu-gauche, .menu-centre').fadeIn('slow');
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


// AJOUT D'UN PROJET
$('#projet-add').on('click', function () {
    $.ajax({
        method: "POST",
        url: "rest/project",
        data: { name: document.getElementById('projet-name').value,
                dod: document.getElementById('projet-dod').value
                }
    }).done(function () {
        refreshProjectList();
        // Clear form
        document.getElementById('projet-name').value = "";
        document.getElementById('projet-dod').value = "";
    });
});

function drop(ev) {
    ev.preventDefault();
    var data=ev.dataTransfer.getData("text/html");
    /* If you use DOM manipulation functions, their default behaviour it not to
     copy but to alter and move elements. By appending a ".cloneNode(true)",
     you will not move the original element, but create a copy. */
    var nodeCopy = document.getElementById(data).cloneNode(true);
    nodeCopy.id = "newId"; /* We cannot use the same ID */
    ev.target.appendChild(nodeCopy);
}

// AJOUT D'UNE TACHE
$('#task-add').on('click', function () {

    $.ajax({
        method: "POST",
        url: "rest/task",
        data: {
            name: document.getElementById('task-name').value,
            idProject : idProjectGlobal,
            idSprint : idSprintGlobal,
            idUserStory : $( "#listUSforTask" ).val()

        }
    }).done(function () {
        loadTasks(idProjectGlobal, idSprintGlobal,"");
        document.getElementById('task-name').value = "";
    });

});

// AJOUT D'UNE US
$('#us-add').on('click', function () {
    console.log((parseInt(document.getElementById('us-te').value)));
    if(isNaN(parseInt(document.getElementById('us-te').value)) || isNaN(parseInt(document.getElementById('us-bv').value))){
        $('#errorModal').modal('show');
    }
    else{
        $.ajax({
            method: "POST",
            url: "rest/userStory",
            data: { name: document.getElementById('us-name').value, te: document.getElementById('us-te').value, bv: document.getElementById('us-bv').value, idProject : idProjectGlobal}
        }).done(function () {
            loadUserStories(idProjectGlobal);
            document.getElementById('us-name').value = "";
            document.getElementById('us-te').value = "";
            document.getElementById('us-bv').value = "";
        });
    }
});

// AJOUT D'UN SPRINT
$('#sprint-add').on('click', function () {
    $.ajax({
        method: "POST",
        url: "rest/sprint",
        data: { name: document.getElementById('sprint-name').value, idProject : idProjectGlobal}
    }).done(function () {
        loadSprints(idProjectGlobal);
        document.getElementById('sprint-name').value = "";
    });
});


// MAIN
$(function () {
    placerScrumzone();
    $('.menu a[data-menu=projets]').click();
    console.log("ready!");
    refreshProjectList();
});


// REFRESH PROJET
 $('#availables_projects').on('click', function () {
     refreshProjectList();
 });


function refreshProjectList () {
    $.ajax({
        method: "GET",
        url: "rest/projects"
    }).done(function (data) {
        console.log(data);
            var projectList = document.getElementById("projet-list");
            projectList.innerHTML ="";
            for (var i = 0; i < data.length; i++) {

                var divProject = document.createElement("div");
                var spanProject = document.createElement("span");
                spanProject.innerText = data[i]["name"];
                divProject.appendChild(spanProject);
                divProject.setAttribute("class","col-md-12 item" );
                var idProject = data[i]["id"];
                var name = data[i]["name"];
               divProject.setAttribute("onclick","afficherActions(); afficherScrumzone();loadDataProject('"+idProject+"','"+name+"');");
                projectList.appendChild(divProject);
            }
        });
}

var idProjectGlobal;
var idSprintGlobal;

function loadDataProject(idProject,name) {


    document.getElementById("projectName").innerHTML = name;

    idSprintGlobal = {};
    var sprintList = document.getElementById("sprint-list");
    sprintList.innerHTML ="";
    var taskList = document.getElementById("task-list");
    taskList.innerHTML ="";
    var US = document.getElementById("us-backlog");
    US.innerHTML ="";

    idProjectGlobal = idProject;

    loadSprints(idProjectGlobal);
    loadUserStories(idProjectGlobal);
}

function loadSprints(idProject)  {
    $.ajax({
        method: "GET",
        url: "rest/sprint",
        data: {
            idProject : idProject
        }
    }).done(function (data) {

        console.log("[SUCCESS] Récupération sprints.");
        var sprintList = document.getElementById("sprint-list");
        sprintList.innerHTML ="";
        for (var i = 0; i < data.length; i++) {

            var divSprint = document.createElement("div");
            var spanSprint = document.createElement("span");
            var iSprint = document.createElement("i");
            spanSprint.innerText = data[i]["name"];
            divSprint.appendChild(spanSprint);
            spanSprint.appendChild(iSprint);
            divSprint.setAttribute("class","col-md-12 item" );
            iSprint.setAttribute("class","md-close" );

            var idSprint = data[i]["id"];
            var nameSprint =  data[i]["name"];
            console.log(data);

            divSprint.setAttribute("onclick","loadTasks('"+idProject+"','"+idSprint+"','"+nameSprint+"');");
            sprintList.appendChild(divSprint);
        }
    }).fail(function () {
        console.log("[ERROR] Récupération sprints. ");
    });
}


function loadUserStories(idProject) {
    $.ajax({
        method: "GET",
        url: "rest/userStories",
        data: {
            idProject : idProject
        }
    }).done(function (data) {
        console.log("[SUCCESS] Récupération User Stories.");
        var backlog = document.getElementById("us-backlog");
        backlog.innerHTML ="";

        var selectUS = document.getElementById("listUSforTask");

        for (var i = 0; i < data.length; i++) {
            var divUS = document.createElement("div");
            var spanUS = document.createElement("span");
            spanUS.innerText = data[i]["name"];
            divUS.appendChild(spanUS);
            divUS.setAttribute("class","col-md-6 item" );
            var idSprint = data[i]["idUS"];

            // Création des otpions pour la liste
            var option = document.createElement("option");
            option.innerHTML = data[i]["name"];
            option.value = data[i]["id"];
            selectUS.appendChild(option);
            //divProject.setAttribute("onclick","afficherActions(); afficherScrumzone();loadDataProject('"+idProject+"');");
            backlog.appendChild(divUS);
        }
        $('.menu-content[data-menu=backlog] .item').draggable();
    }).fail(function () {
        console.log("[ERROR] Récupération User stories. ");
    });
}

function loadTasks(idProject, idSprint,nameSprint) {

    idSprintGlobal = idSprint;

    if (nameSprint !="") {
        document.getElementById("sprintName").innerHTML = nameSprint;
    }
    $.ajax({
        method: "GET",
        url: "rest/task",
        data: {
            idProject : idProject,
            idSprint : idSprint
        }
    }).done(function (data) {
        console.log("[SUCCESS] Récupération User Stories.");
        var taskList = document.getElementById("task-list");
        taskList.innerHTML ="";
        for (var i = 0; i < data.length; i++) {
            var divTask = document.createElement("div");
            var spanTask = document.createElement("span");
            spanTask.innerText = data[i]["name"];
            divTask.appendChild(spanTask);
            divTask.setAttribute("class","col-md-6 item" );
            var idSprint = data[i]["idTask"];
            //divProject.setAttribute("onclick","afficherActions(); afficherScrumzone();loadDataProject('"+idProject+"');");
            taskList.appendChild(divTask);
        }
        $('.menu-content[data-menu=taches] .item').draggable();
    }).fail(function () {
        console.log("[ERROR] Récupération User stories. ");
    });


}

$( ".scrumzone" ).droppable({
    drop: function( event, ui ) {
        $( this )
            .addClass( "ui-state-highlight" )
            .find( "p" )
            .html( "Dropped!" );
    }
});

$( ".scrumzone" ).droppable({
    drop: function( event, ui ) {
        $( this )
            .addClass( "ui-state-highlight" )
            .find( "p" )
            .html( "Dropped!" );
    }
});
