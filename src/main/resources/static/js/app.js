const button = document.getElementById("themeBtn");
const API_URL = getDashboardUrl();

button.addEventListener("click", function () {

    document.body.classList.toggle("dark");

    if(document.body.classList.contains("dark")){

        button.innerHTML="Light Mode";

    }else{

        button.innerHTML="Dark Mode";

    }

});

console.log('Dashboard API URL:', API_URL);
fetch(API_URL)
    .then(res => res.json())
    .then(data => {

    document.getElementById("totalTickets").innerHTML=data.totalTickets;

    document.getElementById("openTickets").innerHTML=data.openTickets;

    document.getElementById("resolvedTickets").innerHTML=data.resolvedTickets;

    document.getElementById("totalChats").innerHTML=data.totalChats;

    let table=document.getElementById("recentTickets");

    table.innerHTML="";

    data.recentTickets.forEach(ticket=>{

    table.innerHTML+=`

    <tr>

    <td>${ticket.id}</td>

    <td>${ticket.title}</td>

    <td>${ticket.status}</td>

    </tr>

    `;

    });

    });

function getDashboardUrl() {
    if ((window.location.hostname === '127.0.0.1' || window.location.hostname === 'localhost') && window.location.port === '5500') {
        return 'http://localhost:8081/dashboard';
    }
    return new URL('dashboard', window.location.href).toString();
}

.then(data => {

document.getElementById("totalTickets").innerHTML=data.totalTickets;

document.getElementById("openTickets").innerHTML=data.openTickets;

document.getElementById("resolvedTickets").innerHTML=data.resolvedTickets;

document.getElementById("totalChats").innerHTML=data.totalChats;

let table=document.getElementById("recentTickets");

table.innerHTML="";

data.recentTickets.forEach(ticket=>{

table.innerHTML+=`

<tr>

<td>${ticket.id}</td>

<td>${ticket.title}</td>

<td>${ticket.status}</td>

</tr>

`;

});

});