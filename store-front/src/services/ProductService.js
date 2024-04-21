import axios from "axios";
import {getToken} from "./AuthService.js";

const BASE_REST_API_URL = 'http://localhost:8080/api/products';

axios.interceptors.request.use(function (config) {

    config.headers['Authorization'] = getToken();

    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

export const getAllProducts = () => axios.get(BASE_REST_API_URL)

export const addProduct = (product) => axios.post(BASE_REST_API_URL, product)

export const getProduct = (id) => axios.get(BASE_REST_API_URL + '/' + id)
export const updateProduct = (id, product) => axios.put(BASE_REST_API_URL + '/' + id, product)
export const deleteProduct = (id) => axios.delete(BASE_REST_API_URL + '/' + id)
