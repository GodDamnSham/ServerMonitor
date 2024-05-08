function fetchData() {
    fetch('http://localhost:8080/monitor/getStatus')
        .then(response => response.json())
        .then(data => {
            const statusTable = document.getElementById('statusTable').getElementsByTagName('tbody')[0];
            statusTable.innerHTML = '';
            for (const serverName in data) {
                const row = statusTable.insertRow();
                const cellOneData = row.insertCell(0);
                const cellTwoData = row.insertCell(1);
                cellOneData.innerHTML = serverName;
                if (data[serverName] === "loading..") {
                    cellTwoData.innerHTML = '<span class="badge badge-primary rounded-pill d-inline"\n' +
                        '              >Loading..</span';
                } else {
                    cellTwoData.innerHTML = data[serverName] ? '<span class="badge badge-success rounded-pill d-inline">Active</span>' : '<span class="badge badge-warning rounded-pill d-inline">Offline</span>';
                }
            }
        })
        .catch(error => console.error('Error:', error));
}
fetchData();
setInterval(fetchData, 1500);
document.getElementById('addDataForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const formData = new FormData(this);
    //console.log(FormData)
    const serverName = formData.get('serverName');
    const serverURL = formData.get('serverURL');
    fetch('http://localhost:8080/monitor', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            serverName: serverName,
            serverURL: serverURL
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to add server data');
            }
            fetchData();
            document.getElementById('serverName').value = '';
            document.getElementById('serverURL').value = '';
        })
        .catch(error => console.error('Error:', error));
});
