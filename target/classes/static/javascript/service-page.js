let cl = document.getElementById("sidebar")
let sidebarMenuButton = document.getElementById("sidebar-menu");
let sidebarUl = document.getElementById("sidebar-ul")

let baseStyle = document.getElementsByClassName("base-style")


let shop = [];

var clickCount = 0;

window.addEventListener('load',()=>{
	// To use the function and retrieve the posts object
let innerhtml = "";
	fetchPosts()
    .then(data => {

        data.forEach(item => {
            /*shop.push(item)*/

            innerhtml += ` 
            <div class="card" style="width: 18rem; height: fit-content;">
                 <img src="/${item.imageUrl}" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">${item.name}</h5>
                        <h3 class="card-title">Rs. ${item.price}</h3>
                        <p class="card-text">${item.description}</p>
                        <a href="#" class="btn btn-primary" id="add-to-bag-${item.p_id}">Add To Bag</a>
                      </div>
            </div>`
        });

        products.innerHTML = innerhtml;

        // Add click event listener to each "Add To Bag" button
        data.forEach(item => {
            document.getElementById(`add-to-bag-${item.p_id}`).addEventListener('click', function(event) {
                addItemToBag(item);
            });
        });

    })
    .catch(error => {
        // Handle error
    });
})


sidebarMenuButton.addEventListener("click", () => {
	if (clickCount % 2 === 0) {
		cl.classList.remove("sidebar");
		cl.classList.add("closed-sidebar");
		sidebarUl.classList.remove("sidebar-ul");
		sidebarUl.classList.add("closed-sidebar-ul");
		sidebarMenuButton.innerText = "menu";
		document.getElementById("contentArea").style.width =  `95%`;
		baseStyle[0].style.width =  `95%`;
		clickCount++;
		//changeWidths(95)
	} else {
		cl.classList.remove("closed-sidebar");
		cl.classList.add("sidebar");
		sidebarUl.classList.remove("closed-sidebar-ul");
		sidebarUl.classList.add("sidebar-ul");
		sidebarMenuButton.innerText = "close";
		document.getElementById("contentArea").style.width = `72%`;
		baseStyle[0].style.width = `70%`;
		//changeWidths(77)
		clickCount++;
	}


})

async function fetchPosts() {
    try {
        const res = await fetch('http://localhost:8080/getAllItems');
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching posts:', error);
        throw error; // Re-throwing the error to be handled by the caller
    }
}
