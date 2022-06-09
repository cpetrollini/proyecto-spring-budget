<!DOCTYPE html>
<html>
	<head>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/home.css"/>
		<script src="https://kit.fontawesome.com/17b640de8e.js" crossorigin="anonymous"></script>
	</head>
	<body>
	  <nav>
		<ul>
			<div>
				<li>MONEY MANAGER</li>
			</div>
			<div class="nav-login">
				<a href=""><li>Balances</li></a>
				<a href=""><li>Logout</li></a>
			</div>
		</ul>
	  </nav>
		<div class = "container">
			<div class="row">
				<div class="col-lg-2 col-md-2"></div>
				<div class="div-mes col-lg-10 col-md-10">
					<h1>JUNIO</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-2 col-md-2 cuentas">
					<div class="agregarCuenta"><h6>CUENTAS</h6><a href="agregar-cuenta.jsp" class="btn-right"><i class="fa fa-plus"></i></a></div>
					<span>Cuenta 1:</span><span> 6000$</span>
				</div>

				<div class="col-lg-10 col-md-10">
					<div class="div-presupuesto">
						<h3>Presupuesto del mes: 30.000$</h3>
						<div class="btn-edit"><a class="btn-right" href="#"><i class="fa fa-pen"></i></a></div>
					</div>
					<div class="div-resumen row">
						<div class="col-md-2 col-lg-2">
						</div>
						<div class="col-md-4 col-lg-4">
							<h5>Te queda: 18.000$</h5>
						</div>
						<div class="col-md-4 col-lg-4">
							<h5>Gastaste: 12.000$</h5>
						</div>
						<div class="col-md-2 col-lg-2"></div>
					</div>

					<div class="progress">
						<div class="progress-bar" role="progressbar" style="width: 15%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100">15% COMIDA</div>
						<div class="progress-bar bg-success" role="progressbar" style="width: 30%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100">30% ALQUILER</div>
						<div class="progress-bar bg-info" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">20% AHORROS</div>
					</div>

					<div class="div-busqueda">
						<div><input type="text" placeholder="Ingrese la etiqueta" name="" id=""><button>Buscar</button></div>
						<a href="#" class="btn-right"><i class="fa fa-plus"></i></a>
					</div>

					<div class="div-detalle">
						<p>Alquiler</p>
						<p>30%</p>
					</div>
				</div>
			</div>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>