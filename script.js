// Database Simulation (Hashing for O(1) Search)
let users = { "S@itej": { pass: "sai@2007", phone: "9876543210", role: "admin" } };
let bookedSlots = {}; // Tracks which slot numbers are taken

function toggleAuth(type) {
    document.getElementById('login-box').style.display = (type === 'login') ? 'block' : 'none';
    document.getElementById('signup-box').style.display = (type === 'signup') ? 'block' : 'none';
}

function handleSignup() {
    let u = document.getElementById('s-user').value;
    let p = document.getElementById('s-pass').value;
    let ph = document.getElementById('s-phone').value;
    if(u && p && ph) {
        users[u] = { pass: p, phone: ph, role: "user" };
        alert("Account Created! You can now login.");
        toggleAuth('login');
    } else { alert("Error: Fill all fields!"); }
}

function handleLogin() {
    let u = document.getElementById('l-user').value;
    let p = document.getElementById('l-pass').value;

    if(users[u] && users[u].pass === p) {
        document.getElementById('auth-layer').style.display = 'none';
        if(users[u].role === "admin") {
            document.getElementById('admin-container').style.display = 'block';
        } else {
            document.getElementById('user-tag').innerText = u;
            document.getElementById('user-container').style.display = 'block';
        }
    } else { alert("Invalid Credentials!"); }
}

function bookSlot() {
    let name = document.getElementById('u-name').value;
    let phone = document.getElementById('u-phone').value;
    let car = document.getElementById('car-list').value;
    let charger = document.getElementById('charger-type').value;
    let slot = document.getElementById('slot-select').value;
    let time = document.getElementById('start-time').value;

    if(!name || !phone || !time) { alert("Error: Please enter Name, Phone, and Time!"); return; }

    // Collision Check: Is the slot already taken?
    if(bookedSlots[slot]) {
        alert(`Slot ${slot} is already booked by ${bookedSlots[slot].name}. Please choose another.`);
        return;
    }

    // Save Data
    bookedSlots[slot] = { name, phone, car, charger, time };

    // Log to Admin Table
    let table = document.getElementById('admin-log');
    table.innerHTML += `<tr>
        <td><b>Slot ${slot}</b></td>
        <td>${name}</td>
        <td>${phone}</td>
        <td>${car}</td>
        <td>${charger}</td>
        <td>${time}</td>
    </tr>`;

    document.getElementById('load').innerText = Object.keys(bookedSlots).length;
    alert(`Success! Slot ${slot} booked for ${name} at ${time}.`);
}

function forgot() {
    let u = prompt("Enter Username:");
    if(users[u]) {
        let ph = prompt("Verify Full Mobile Number:");
        if(ph === users[u].phone) alert("Verified! Password: " + users[u].pass);
        else alert("Incorrect phone number.");
    } else { alert("User not found."); }
}

function logout() { location.reload(); }