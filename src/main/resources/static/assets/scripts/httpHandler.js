export const sendHttpRequest = (method, url, responseType, data) => {
    return new Promise((resolve, reject) => {
        const xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open(method, url, true);
        xmlHttpRequest.responseType = responseType;
        xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8')
        xmlHttpRequest.onload = () => {
            if (xmlHttpRequest.status >= 200 && xmlHttpRequest.status < 500) {
                resolve(xmlHttpRequest.response);
            } else {
                reject(new Error('Something went wrong!'));
            }
        }
        xmlHttpRequest.onerror = () => {
            reject(new Error('Failed to send request'));
        }
        xmlHttpRequest.send(JSON.stringify(data));
    });
}

export const redirect = url => {
    location.href = url;
}

export const urlPrefix = 'http://localhost:8081/';