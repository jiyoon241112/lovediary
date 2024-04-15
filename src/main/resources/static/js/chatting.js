const socket = io('ws://localhost:8080');
const couple = $("#couple_idx").val();

socket.on('message', couple_idx => {
    if(couple == couple_idx) {
        console.log("here");
    }
})

socket.emit('message', couple);