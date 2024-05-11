const menuItem = document.getElementById("menuButton");
const navlinks = document.getElementsByClassName("navlinks");
let menuItemClickCount = 0;
let Myheight = 3;
let updateButtonClick = 0;
let update = {}


// Retrievs the USerProfile Information from DB
async function getUserProfile() {
	try {
		const res = await fetch('http://localhost:8080/get/profile');
		const user = await res.json();
		return user;
	} catch (error) {

		console.error('Error fetching Profile:', error);
		throw error; // Re-throwing the error to be handled by the caller

	}
}

// Removes the USerProfile Information from DB
function removeprofile() {
	if (confirm('Are You Sure !!! ')) {
		getUserProfile().then(async (user) => {
			try {
				const res = await fetch('http://localhost:8080/deleteUser', {
					method: 'DELETE',
					headers: { 'Content-type': 'application/json' },
					body: JSON.stringify(user)
				})

				const deleteUser = await res.json();
				logout();
				console.log(deleteUser)
			} catch (error) {

				console.error('Error fetching Profile:', error);
				throw error; // Re-throwing the error to be handled by the caller

			}
		})

	}
	else {
		//return;
	}
}

// Update the USerProfile Information from DB
async function updateUser(update) {
	try {
		const res = await fetch('http://localhost:8080/updateUser', {
			method: 'PUT',
			headers: { 'Content-type': 'application/json' },
			body: JSON.stringify(update)
		})

		const user = await res.json();
		return user;
	} catch (error) {

		console.error('Error fetching Profile:', error);
		throw error; // Re-throwing the error to be handled by the caller

	}


}

function updateprofile() {
	if (updateButtonClick % 2 == 0) {
		document.getElementById('username').disabled = false;
		document.getElementById('email').disabled = false;
		document.getElementById('number').disabled = false;
		document.getElementById('updateButton').innerText = 'Save Changes'
		document.getElementById('updateButton').classList.remove('btn-primary')
		document.getElementById('updateButton').classList.add('btn-success')
	} else {
		console.log('hello')
		update = {
			email: document.getElementById('email').value,
			enabled: true,
			id: 902,
			password: "$2a$10$S.vAHoEjK9Mf1eXFld00zOp/mmmkTAWrr3biwl52KLL9xJlSMRBqu",
			phone: document.getElementById('number').value,
			role: "ROLE_USER",
			username: document.getElementById('username').value
		}

		// Put request to update item
		updateUser(update).then((user) => {
			console.log('user', user)
			document.getElementById('username').disabled = true;
			document.getElementById('email').disabled = true;
			document.getElementById('number').disabled = true;
			alert("Yout Profile Information Just Got Changed !!!")
			logout();
			document.getElementById('updateButton').innerText = 'Update Profile'
			document.getElementById('updateButton').classList.remove('btn-success')
			document.getElementById('updateButton').classList.add('btn-primary')
		}
		)
	}
	updateButtonClick++;
}


// Load cart data from local storage when the page loads
window.addEventListener('load', getUserProfile()
	.then((user) => {
		console.log(user)
		document.getElementById('username').value = user.username;
		document.getElementById('email').value = user.email;
		document.getElementById('number').value = user.phone;

	})

)






window.addEventListener('resize', function(event) {
	if (window.innerWidth <= 800) {
		navlinks[0].style.visibility = "hidden"
		navlinks[1].style.visibility = "hidden"
		document.getElementsByClassName('header')[0].style.height = 3 + "em";
	}
	else if (window.innerWidth > 801 && window.innerWidth < 1501) {
		navlinks[0].style.visibility = "visible"
		navlinks[1].style.visibility = "visible"
		document.getElementsByClassName('header')[0].style.height = 3 + "em";
	}

	else if (window.innerWidth > 1501) {
		navlinks[0].style.visibility = "visible"
		navlinks[1].style.visibility = "visible"
		document.getElementsByClassName('header')[0].style.height = 3 + "em";
	}
});


const menuItemfunction = () => {
	/*alert(Myheight)*/
	const header = document.getElementsByClassName('header')[0]
	var id = null;
	clearInterval(id);
	console.log(Myheight)
	if (Myheight >= 3 && Myheight < 27) {
		id = setInterval(function() {

			if (Myheight >= 27) {
				clearInterval(id)
				menuItem.style.transform = 'rotate(' + 90 + 'deg)';
				navlinks[0].style.visibility = "visible"
				navlinks[1].style.visibility = "visible"
			} else {
				header.style.height = Myheight + "em";
				Myheight += .5;
			}

		}, 1);
	} else if (Myheight >= 27) {
		id = setInterval(function() {
			navlinks[0].style.visibility = "hidden"
			navlinks[1].style.visibility = "hidden"
			if (Myheight <= 3.1) {
				clearInterval(id)
				menuItem.style.transform = 'rotate(' + 180 + 'deg)';
			} else {
				header.style.height = Myheight + "em";
				Myheight -= .5;
			}

		}, 1);

	}

}

menuItem.addEventListener("click", menuItemfunction)

// Donation Form's Donate money Online Button
document.getElementById('donate-cash-online').addEventListener('click', () => {
	document.getElementById("myForm").style.display = "block";
})

function donateOnline() {
	let amount = document.getElementById('online-amt').value
	let name = document.getElementById('online-name').value
	let mail = document.getElementById('online-mail').value
	let contact = document.getElementById('online-number').value

	if (!checkDonationFormValidation()) {
		return;
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
				alert('Payment successful: ' + response.razorpay_payment_id);
				//	localStorage.clear()
			},
			prefill: {
				name: name, // Buyer's name
				email: mail, // Buyer's email
				contact: contact // Buyer's contact number
			},
			theme: {
				color: '#F37254' // Customize the checkout theme color
			}
		};

		var rzp = new Razorpay(options);
		rzp.open();
	}
}

function checkDonationFormValidation() {
	let mark = true;
	if (document.getElementById('online-name').value.length < 6) {
		document.getElementById('form-n').innerText = 'Enter A Name With Atlest 5 Charcters'
		mark = false;
	}
	if (document.getElementById('online-number').value.length < 10) {
		document.getElementById('form-pn').innerText = 'Enter A Valid Number  '
		mark = false;
	}
	if (Number(document.getElementById('online-amt').value) < 10) {
		document.getElementById('form-amt').innerText = 'Enter A Name With Atlest 5 Charcters'
		mark = false;
	}

	return mark;
}


function closeForm() {
	document.getElementById("myForm").style.display = "none";
}




