export const API_BASE_URL: string = `http://localhost:9292/`;
export const ACCESS_TOKEN = 'ACCESS_TOKEN';

export const OAUTH2_REDIRECT_URI = 'http://localhost:4200/oauth2-redirect';

export const GOOGLE_AUTH_URL = API_BASE_URL + 'oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL + 'oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;