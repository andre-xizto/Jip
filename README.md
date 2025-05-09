# 🌐 EasyIP

**EasyIP** is a clean and simple REST API built with **Java 21** and **Spring Boot**, designed to:

- 📡 Retrieve the **IP address** of incoming requests
- 🌍 Get **geolocation** information based on an IP address

---

## 🚀 Endpoints

### 📥 Get Client IP
**GET** `/api/ip`

Returns the public IP address of the requester.

#### Response Example
```json
{
  "ip": "123.123.123.123"
}
```

---

### 🌎 Get IP Geolocation
**GET** `/api/geo?ip=<IP>`

Returns geolocation details for a given IP address.

#### Query Parameters
- `ip` (required): the IP address to lookup

#### Response Example
```json
{
  "asn": "AS12345",
  "aso": "Example ISP",
  "city": "San Francisco",
  "continent": "North America",
  "country": "United States",
  "latitude": "37.7749",
  "longitude": "-122.4194",
  "postal": "94103"
}
```

---

## 🔰 How to run

To run this project is simple

1. Access docker folder
```bash
cd ./docker
```

2. Then run this command
```bash
docker compose up -d
```

You will see something like this:
```bash
Container easyip-api    Started 
```

So congratulations! You started the API.

---

## 🛠️ Tech Stack
- Java 21
- Spring Boot
- REST API

---

## 👨‍💻 Author
Developed with ❤️ by [André Xizto](https://github.com/andre-xizto)

---

## 📄 License
This project is open-source and available under the MIT License.

---

> Simple. Fast. Useful. That’s **EasyIP**.

