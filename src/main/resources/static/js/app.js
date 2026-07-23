const button = document.getElementById("themeBtn");
const API_BASE = window.location.hostname === '127.0.0.1' && window.location.port === '5500' ? 'http://localhost:8081' : '';

button.addEventListener("click", function () {

    document.body.classList.toggle("dark");

    if(document.body.classList.contains("dark")){

        button.innerHTML="Light Mode";

    }else{

        button.innerHTML="Dark Mode";

    }

});

fetch(`${API_BASE}/dashboard`)

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