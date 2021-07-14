function solve() {
  let mainPage =  document.getElementsByClassName
  ('shopping-cart')[0];
  let textArea = document.getElementsByTagName
  ('textarea')[0];
  mainPage.addEventListener("click", addItem);
  let checkout = document.getElementsByClassName("checkout")[0];
  checkout.addEventListener("click", onCheckOut);

  let boughtProducts = [];

  function addItem(event) {
     if (event.target.nodeName === "BUTTON" &&
     event.target.className === 'add-product') {
        let name;
        Array.from(document.getElementsByClassName("product-title")).forEach(x => {
           if (x.parentElement.parentElement === event.target.parentElement.parentElement) {
              name =  x.textContent;
           }
        } );
   
        let price;
        Array.from(document.getElementsByClassName("product-line-price")).forEach(x => {
         if (x.parentElement === event.target.parentElement.parentElement) {
            price =  Number(x.textContent);
         }
      } );


      let alreadyBought = false;
      for (let element of boughtProducts) {
         if (element.name === name) {
            element.price += price;
            alreadyBought = true;
         }
      }
      if (!alreadyBought) {
         boughtProducts.push({name, price});
      }
      console.log(boughtProducts);
        textArea.textContent += `Added ${name} for ${price.toFixed(2)} to the cart.\n`; 
     }
  }

  function onCheckOut() {
     let itemNames = [];
     let sum = 0;
     boughtProducts.forEach(x => {
        itemNames.push(x.name);
        sum += x.price;
     })
     textArea.textContent += `You bought ${itemNames.join(", ")} for ${sum.toFixed(2)}.`
     let buttons = Array.from(document.getElementsByTagName("button")).forEach(x => x.disabled = true);
  }




}