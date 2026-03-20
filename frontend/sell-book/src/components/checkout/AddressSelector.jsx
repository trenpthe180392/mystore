import React, { useState, useEffect } from 'react';
import { AddressService } from '../../services/AddressService';

const AddressSelector = ({ onAddressFinal }) => {
  const [data, setData] = useState({ provinces: [], districts: [], wards: [] });
  const [selected, setSelected] = useState({ p: "", d: "", w: "", street: "" });

  // Khởi tạo lấy danh sách Tỉnh/Thành
  useEffect(() => {
    AddressService.getProvinces().then(res => setData(prev => ({ ...prev, provinces: res.data })));
  }, []);

  // Khi chọn Tỉnh -> Lấy Huyện
  const handleProvinceChange = async (e) => {
    const pCode = e.target.value;
    const pName = data.provinces.find(i => i.code == pCode)?.name || "";
    setSelected({ p: pName, d: "", w: "", street: selected.street });
    setData(prev => ({ ...prev, districts: [], wards: [] }));

    if (pCode) {
      const res = await AddressService.getDistricts(pCode);
      setData(prev => ({ ...prev, districts: res.data.districts }));
    }
  };

  // Khi chọn Huyện -> Lấy Xã
  const handleDistrictChange = async (e) => {
    const dCode = e.target.value;
    const dName = data.districts.find(i => i.code == dCode)?.name || "";
    setSelected(prev => ({ ...prev, d: dName, w: "" }));

    if (dCode) {
      const res = await AddressService.getWards(dCode);
      setData(prev => ({ ...prev, wards: res.data.wards }));
    }
  };

  // Gửi địa chỉ hoàn chỉnh về CheckoutPage mỗi khi có thay đổi
  useEffect(() => {
    const { street, w, d, p } = selected;
    if (p && d && w && street) {
      onAddressFinal(`${street}, ${w}, ${d}, ${p}`);
    } else {
      onAddressFinal(""); // Trả về rỗng nếu chưa nhập đủ
    }
  }, [selected]);

  return (
    <div className="address-container">
      <div className="address-grid">
        <select className="amazon-input" onChange={handleProvinceChange}>
          <option value="">Tỉnh / Thành phố</option>
          {data.provinces.map(p => <option key={p.code} value={p.code}>{p.name}</option>)}
        </select>

        <select className="amazon-input" onChange={handleDistrictChange} disabled={!selected.p}>
          <option value="">Quận / Huyện</option>
          {data.districts.map(d => <option key={d.code} value={d.code}>{d.name}</option>)}
        </select>

        <select className="amazon-input" 
                onChange={(e) => setSelected(prev => ({...prev, w: data.wards.find(i => i.code == e.target.value)?.name || ""}))}
                disabled={!selected.d}>
          <option value="">Phường / Xã</option>
          {data.wards.map(w => <option key={w.code} value={w.code}>{w.name}</option>)}
        </select>
      </div>

      <input 
        type="text" 
        className="amazon-input street-input" 
        placeholder="Số nhà, tên đường..." 
        value={selected.street}
        onChange={(e) => setSelected(prev => ({ ...prev, street: e.target.value }))}
      />
    </div>
  );
};

export default AddressSelector;