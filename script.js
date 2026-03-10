// Sample data for charging slots
const chargingSlots = [
    { id: 1, name: "Slot A (Fast Charger)", isBooked: false },
    { id: 2, name: "Slot B (Standard)", isBooked: false },
    { id: 3, name: "Slot C (Fast Charger)", isBooked: true },
    { id: 4, name: "Slot D (Standard)", isBooked: false },
];

// Function to render slots to the UI
function renderSlots() {
    const slotContainer = document.getElementById('slot-container');
    slotContainer.innerHTML = ''; // Clear existing content

    chargingSlots.forEach(slot => {
        const slotDiv = document.createElement('div');
        slotDiv.className = `slot ${slot.isBooked ? 'booked' : 'available'}`;
        
        slotDiv.innerHTML = `
            <span>${slot.name}</span>
            <span class="status-badge">${slot.isBooked ? 'Reserved' : 'Available'}</span>
            ${!slot.isBooked ? `<button onclick="bookSlot(${slot.id})">Book Now</button>` : ''}
        `;
        
        slotContainer.appendChild(slotDiv);
    });
}

// Function to handle the booking logic
function bookSlot(slotId) {
    const slot = chargingSlots.find(s => s.id === slotId);
    
    if (slot && !slot.isBooked) {
        slot.isBooked = true;
        alert(`Successfully booked ${slot.name}!`);
        renderSlots(); // Refresh the UI
    } else {
        alert("Sorry, this slot is already taken.");
    }
}

// Initialize the app
document.addEventListener('DOMContentLoaded', renderSlots);