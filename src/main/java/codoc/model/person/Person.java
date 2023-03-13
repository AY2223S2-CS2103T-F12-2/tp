package codoc.model.person;

import static codoc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import codoc.model.module.Module;
import codoc.model.skill.Skill;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Github github;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Skill> skills = new HashSet<>();
    private final Set<Module> modules = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Github github, Email email, Address address, Set<Skill> skills, Set<Module> modules) {
        requireAllNonNull(name, github, email, address, skills, modules);
        this.name = name;
        this.github = github;
        this.email = email;
        this.address = address;
        this.skills.addAll(skills);
        this.modules.addAll(modules);
    }

    /**
     * Overloaded constructor to be remove once Mod is fully implemented.
     */
    public Person(Name name, Github github, Email email, Address address, Set<Skill> skills) {
        requireAllNonNull(name, github, email, address, skills);
        this.name = name;
        this.github = github;
        this.email = email;
        this.address = address;
        this.skills.addAll(skills);
        this.modules.addAll(modules);
    }

    public Name getName() {
        return name;
    }

    public Github getGithub() {
        return github;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }
    /**
     * Returns an immutable Module List, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getGithub().equals(getGithub())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getSkills().equals(getSkills())
                && otherPerson.getModules().equals(getModules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, github, email, address, skills, modules);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Github: ")
                .append(getGithub())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Skill> skills = getSkills();
        if (!skills.isEmpty()) {
            builder.append("; Skills: ");
            skills.forEach(builder::append);
        }
        if (!modules.isEmpty()) {
            builder.append("; Modules: ");
            modules.forEach(builder::append);
        }
        return builder.toString();
    }

}