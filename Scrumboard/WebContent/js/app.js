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



// AJOUT D'UNE TACHE
$('#task-add').on('click', function () {

    $.ajax({
        method: "POST",
        url: "rest/task",
        data: {
            name: document.getElementById('task-name').value,
            idProject : idProjectGlobal,
            idSprint : idSprintGlobal
        }
    }).done(function () {
        loadTasks(idProjectGlobal, idSprintGlobal);
        document.getElementById('task-name').value = "";
    });

});

// AJOUT D'UNE US
$('#us-add').on('click', function () {
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
            var projectList = document.getElementById("projet-list");
            projectList.innerHTML ="";
            for (var i = 0; i < data.length; i++) {
                var divProject = document.createElement("div");
                var spanProject = document.createElement("span");
                spanProject.innerText = data[i]["name"];
                divProject.appendChild(spanProject);
                divProject.setAttribute("class","col-md-12 item" );
                var idProject = data[i]["id"];
               divProject.setAttribute("onclick","afficherActions(); afficherScrumzone();loadDataProject('"+idProject+"');");
                projectList.appendChild(divProject);
            }
        });
}

var idProjectGlobal;
var idSprintGlobal;

function loadDataProject(idProject) {

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
            spanSprint.innerText = data[i]["name"];
            divSprint.appendChild(spanSprint);
            divSprint.setAttribute("class","col-md-12 item" );
            var idSprint = data[i]["id"];
            console.log(data[i]);

            divSprint.setAttribute("onclick","loadTasks('"+idProject+"','"+idSprint+"');");
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
        for (var i = 0; i < data.length; i++) {
            var divUS = document.createElement("div");
            var spanUS = document.createElement("span");
            spanUS.innerText = data[i]["name"];
            divUS.appendChild(spanUS);
            divUS.setAttribute("class","col-md-12 item" );
            var idSprint = data[i]["idUS"];
            //divProject.setAttribute("onclick","afficherActions(); afficherScrumzone();loadDataProject('"+idProject+"');");
            backlog.appendChild(divUS);
        }
    }).fail(function () {
        console.log("[ERROR] Récupération User stories. ");
    });
}

function loadTasks(idProject, idSprint) {

    idSprintGlobal = idSprint;
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
            divTask.setAttribute("class","col-md-12 item" );
            var idSprint = data[i]["idTask"];
            //divProject.setAttribute("onclick","afficherActions(); afficherScrumzone();loadDataProject('"+idProject+"');");
            taskList.appendChild(divTask);
        }
    }).fail(function () {
        console.log("[ERROR] Récupération User stories. ");
    });


}