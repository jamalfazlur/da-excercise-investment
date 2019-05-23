-- INITIAL DATA FOR API INVESTMENT --

INSERT INTO USER (USER_ID, USER_NAMA, USER_ALAMAT, USER_SALDO) VALUES
(1, 'Jamal', 'Depok', 5000000),
(2, 'Zulfikar', 'Tangerang', 4300000),
(3, 'Azis', 'Bekasi', 3500000),
(4, 'Rizal', 'Bandung', 3200000),
(5, 'Muchlis', 'Jakarta', 4800000);

INSERT INTO INVESTMENT (ID_SBN, HARGA_SATUAN, IMBALAN, NAMA_SBN, PAJAK) VALUES
('ST', 1000000, 0.0815, 'Sukuk Tabungan', 0.15),
('SBR', 1000000, 0.0795, 'Savings Bond Ritel', 0.15);