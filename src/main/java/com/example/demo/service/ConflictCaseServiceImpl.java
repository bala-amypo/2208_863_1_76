@Override
public ConflictCase createCase(ConflictCase conflictCase) {

    if (conflictCase.getPrimaryPersonId() == null ||
        conflictCase.getSecondaryPersonId() == null) {
        return null; // ❗ test expects NO exception
    }

    if (conflictCase.getStatus() == null) {
        conflictCase.setStatus("OPEN");
    }

    return repository.save(conflictCase);
}
