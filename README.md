Bookstore Web Application
=========

A book registration project using Java + Servlet + JSP + TomCat + Postgres + PgAdmin + Docker.

Getting started
---------------

Download [Docker Desktop](https://www.docker.com/products/docker-desktop) for Mac or Windows. [Docker Compose](https://docs.docker.com/compose) will be automatically installed. On Linux, make sure you have the latest version of [Compose](https://docs.docker.com/compose/install/). 


## Linux Containers

The Linux stack uses Python, Node.js, .NET Core (or optionally Java), with Redis for messaging and Postgres for storage.

> If you're using [Docker Desktop on Windows](https://store.docker.com/editions/community/docker-ce-desktop-windows), you can run the Linux version by [switching to Linux containers](https://docs.docker.com/docker-for-windows/#switch-between-windows-and-linux-containers), or run the Windows containers version.

Run in this directory:
```
docker-compose up
```

Acess the Pgadmin using [http://localhost:8080](http://localhost:8080) and create the book table structure:

```sql
CREATE TABLE book(
	id serial PRIMARY KEY,
	title VARCHAR (250) NOT NULL,
	author VARCHAR (50) NOT NULL
);
```

The app will be running at [http://localhost:8383/bookstore](http://localhost:8383/bookstore).

