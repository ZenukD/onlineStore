import React, {useEffect, useState} from "react";
import {addProduct, getProduct, updateProduct} from "../services/ProductService.js";
import {useNavigate, useParams} from "react-router-dom";

const AddProductComponent = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [image, setImage] = useState(null);

    const navigate = useNavigate();
    const {id} = useParams()

    const isEmpty = (value, fieldName) => {
        if (!value.trim()) {
            alert(`Product ${fieldName} cannot be empty`);
            return true;
        }
        return false;
    };

    const saveOrUpdateProduct = (e) => {
        e.preventDefault();

        if (isEmpty(name, 'name') || isEmpty(description, 'description') || isEmpty(price, 'price')) {
            return;
        }

        const product = {name, description, price, image};


        if (id) {
            updateProduct(id, product)
                .then((response) => {
                    console.log(response.data);
                    navigate('/products')
                })
                .catch(error => {
                    console.error(error);
                })
        } else {
            addProduct(product)
                .then((response) => {
                    console.log(response.data);
                    navigate('/products');
                })
                .catch(error => {
                    console.error(error);
                })
        }
    }

    function pageTitle() {
        if (id) {
            return <h2 className='text-center'>Update Product</h2>;
        } else {
            return <h2 className='text-center'>Add Product</h2>;
        }
    }

    useEffect(() => {
        if (id) {
            getProduct(id).then((response) => {
                console.log(response.data);
                setName(response.data.name);
                setDescription(response.data.description);
                setPrice(response.data.price);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [id]);

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setImage(file);
    };

    return (
        <div className='container'>
            <br/><br/>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {pageTitle()}
                <div className='card-body'>
                    <form encType="multipart/form-data">
                        <div className='form-droup mb-2'>
                            <label className='form-label'>Product name</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter product name'
                                name='name'
                                value={name}
                                onChange={(e) => setName(e.target.value)}/>
                        </div>

                        <div className='form-droup mb-2'>
                            <label className='form-label'>Product description</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter product description'
                                name='description'
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}/>
                        </div>

                        <div className='form-droup mb-2'>
                            <label className='form-label'>Product price</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter product price'
                                name='price'
                                value={price}
                                onChange={(e) => setPrice(e.target.value)}/>
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Product image</label>
                            <div className='custom-file'>
                                <input
                                    type='file'
                                    className='form-control-file'
                                    accept='image/*'
                                    onChange={handleImageChange}
                                />
                            </div>
                        </div>

                        <button className='btn btn-success' onClick={(e) => saveOrUpdateProduct(e)}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AddProductComponent;
