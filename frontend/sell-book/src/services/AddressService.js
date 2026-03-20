import axios from 'axios';

const BASE_URL = 'https://provinces.open-api.vn/api';

export const AddressService = {
  getProvinces: () => axios.get(`${BASE_URL}/p/`),
  getDistricts: (pCode) => axios.get(`${BASE_URL}/p/${pCode}?depth=2`),
  getWards: (dCode) => axios.get(`${BASE_URL}/d/${dCode}?depth=2`),
};