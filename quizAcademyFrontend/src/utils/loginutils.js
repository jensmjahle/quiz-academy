export function isLoginSuccessful (response) {
    if (response == undefined || response == null ) return false;
    if (response.data == undefined || response.data == null ) return false;
    return response.data.status == "true";
}
