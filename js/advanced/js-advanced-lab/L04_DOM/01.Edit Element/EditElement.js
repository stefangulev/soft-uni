function editElement(reference, match, replacer) {
        let matcher = new RegExp(match, 'g'); 
        let newString = reference.textContent.replace(matcher, replacer); 
        reference.textContent = newString;
}