
// Load cart data from local storage when the page loads
let email = null;
window.addEventListener('load', async function() {
	try {
		const res = await fetch('http://localhost:8080/get/profile');
		const userData = await res.json();
		document.getElementById('username').value = userData.username
		document.getElementById('number').value = userData.phone
		email = userData.email;
	} catch (error) {

		console.error('Error fetching Profile:', error);
		throw error; // Re-throwing the error to be handled by the caller

	}

	makePayment()
});



document.getElementById("pay-now-button").addEventListener('click', () => {
	let amount = localStorage.getItem("price");

	if (!validateInputs()) {
		alert("Please enter all the required fields")
	} else {


		var options = {
			key: 'rzp_test_KzfyJqkNOcGlv7', // Replace with your Razorpay API key
			amount: Number(amount) + "00", // Amount in smallest currency unit (e.g., paisa for INR)
			currency: 'INR', // Currency code (e.g., INR for Indian Rupees)
			name: 'Temple Trsut Donation',
			description: 'Items Realted To Temple and GOd',
			image: null, // Logo or image URL
			handler: function(response) {
				// Handle successful payment

				saveOrderToBackend();
				localStorage.clear();
				alert('Payment successful: ' + response.razorpay_payment_id);
				location.reload();
	
			},
			prefill: {
				name: document.getElementById('username').value, // Buyer's name
				email: email, // Buyer's email
				contact: document.getElementById('number').value // Buyer's contact number
			},
			theme: {
				color: '#F37254' // Customize the checkout theme color
			}
		};

		var rzp = new Razorpay(options);
		rzp.open();
	}
})

function makePayment() {
	document.getElementById("amount").value = localStorage.getItem("price")
	document.getElementById("amount").readOnly = true
}

function validateInputs() {
	if (document.getElementById('username').value === "" || document.getElementById('address').value === ""
		|| document.getElementById('pincode').value === "" || document.getElementById('number').value === "") {
		return false;
	} else {
		return true;
	}
}
async function saveOrderToBackend() {
	let orderArray = []
	bagArray.map((item) => {
		orderDesc = {
			customerName: document.getElementById("username").value,
			customerAddress: document.getElementById("address").value,
			pincode: document.getElementById("pincode").value,
			number: document.getElementById("number").value,

			itemId: item.p_id,
			itemName: item.name,
			itemPrice: item.price,
		}

		orderArray.push(orderDesc);
		orderDesc = null;
	})
	const res = fetch("/order", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(orderArray)
	})

	if (!res.ok) {
		throw new Error("Error while saving order")
	}

	let savedOrders = res.json();
	downloadReceipt();
	console.log(savedOrders)
}


function downloadReceipt() {
	var element = document.getElementById('content');

	var options = {
		filename: 'portionToSave.pdf',
		image: { type: 'jpeg', quality: 0.98 },
		html2canvas: { scale: 2 },
		jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
	};

	// Generate PDF from HTML content using html2pdf.js
	html2pdf().from(element.innerHTML).set(options).save();
	/*window.location.href = '/admin/shop';*/
}



