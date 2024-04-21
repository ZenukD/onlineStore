import axios from "axios";

const BASE_USER_REST_API_URL = 'http://localhost:8080/api/user'

export const updateUser = (username, user) => axios.patch(BASE_USER_REST_API_URL + '/' + username, user)