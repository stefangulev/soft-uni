function validate (obj) {
    let methods = ["GET", "POST", "DELETE", "CONNECT"];
    if (!methods.includes(obj["method"]) || obj["method"] === undefined) {
        throw new Error("Invalid request header: Invalid Method");
    }

    if(!/^[a-zA-Z0-9\.]+$/.test(obj["uri"]) || obj["uri"] === undefined) {
        throw new Error("Invalid request header: Invalid URI");
    }

    let versions = ["HTTP/0.9", "HTTP/1.0", "HTTP/1.1", "HTTP/2.0"]
    if (!versions.includes(obj["version"]) || obj["version"] === undefined) {
        throw new Error("Invalid request header: Invalid Version");
    }

    if(!/^[^<>\\&'"]*$/.test(obj["message"]) || obj["message"] === undefined) {
        throw new Error("Invalid request header: Invalid Message");
    }

    return obj;
}

console.log(validate({
    method: 'POST',
    uri: 'home.bash',
    message: 'rm -rf /*'
  }  
  ));
