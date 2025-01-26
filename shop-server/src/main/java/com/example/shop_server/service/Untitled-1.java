@PutMapping
public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) {
    return ResponseEntity.ok(service.updateCategory(category));
}
