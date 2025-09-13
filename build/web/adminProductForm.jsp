<%@ include file="header.jsp" %>
<%@ page import="DB.Product" %>

<div class="container my-4">
<%
    String action = (String) request.getAttribute("action");
    Product product = (Product) request.getAttribute("product");
    int id = product != null ? product.getId() : 0;
    String name = product != null ? product.getName() : "";
    double price = product != null ? product.getPrice() : 0;
%>

<h2><%= "edit".equals(action) ? "Edit" : "Add" %> Product</h2>

<form action="AdminProductServlet" method="post" class="needs-validation" novalidate>
    <input type="hidden" name="action" value="<%= action %>" />
    <input type="hidden" name="id" value="<%= id %>" />
    
    <div class="mb-3">
        <label for="productName" class="form-label">Name:</label>
        <input type="text" class="form-control" id="productName" name="name" value="<%= name %>" required />
        <div class="invalid-feedback">Please enter a product name.</div>
    </div>
    
    <div class="mb-3">
        <label for="productPrice" class="form-label">Price:</label>
        <input type="number" class="form-control" id="productPrice" name="price" step="0.01" value="<%= price %>" required />
        <div class="invalid-feedback">Please enter a valid price.</div>
    </div>
    
    <button type="submit" class="btn btn-primary"><%= "edit".equals(action) ? "Update" : "Add" %> Product</button>
</form>

<a href="AdminProductServlet?action=list" class="btn btn-secondary mt-3">Back to Product List</a>

</div>

<script>
// Bootstrap 5 form validation script
(() => {
  'use strict'
  const forms = document.querySelectorAll('.needs-validation')
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
      }
      form.classList.add('was-validated')
    }, false)
  })
})()
</script>
