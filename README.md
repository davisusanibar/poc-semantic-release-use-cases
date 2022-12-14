# Semantic Release Proposal for Java Libraries

## Sonatype OSSRH (OSS Repository Hosting)

Create a ticket with Sonatype: Sonatype uses JIRA to manage requests.

1. Create your JIRA account - https://issues.sonatype.org/secure/Signup!default.jspa
2. Create a New Project ticket - https://issues.sonatype.org/secure/CreateIssue.jspa?pid=10134&issuetype=21

## Signing Java Artifacts

1. Generate public/private key with gpg commands

```shell
gpg --full-generate-key
gpg --keyring signgithubjars.gpg --export-secret-keys > ~/.gnupg/signgithubjars.gpg
```

## Publish Java Artifacts

1. Publish artifacts locally to Maven:
```shell
./gradlew
    clean
    publishMavenPublicationToLocalRepository
    -console=verbose
    -Psigning.keyId=XXXXXXXX
    -Psigning.password=AAAAAAA
    -Psigning.secretKeyRingFile=/Users/dsusanibar/.gnupg/signgithubjars.gpg
```

2. Publish artifacts to Sonatype:
```shell
./gradlew \
    clean \
    publishToSonatype \
    -Psigning.keyId=XXXXXXXX \
    -Psigning.password=AAAAAAA \
    -Psigning.secretKeyRingFile=/Users/dsusanibar/.gnupg/signgithubjars.gpg \
    -Psonatype.user=XXXXXXXX \
    -Psonatype.password=AAAAAAA \
    -PGITHUB_ACTOR=david
```

## Semantic-release Configuration


## Test release candidate changes

```shell
main < next < next-major < beta < alpha

Test: Change as a maintenance feature: feat: feature 001
Test: Change as a maintenance feature + break: feat: feature 001 !BREAKING CHANGE
Test: Change as a maintenance feature: feat: feature 002
Test: Main: Change as a maintenance feature: feat: feature 003 with next branch just created
OK: From: v1.1.0 To: v1.2.0
Test: Main: Change as a maintenance feature: feat: feature 004 with next-major branch just created
OK: From: v1.2.0 To: v1.3.0
Test: Main: Change as a maintenance feature: feat: feature 005 with next-major branch just created + maintenance branch added
OK: From: v1.3.0 To: v1.4.0

git checkout -b 1.x v1.3.0

// nuevo inicio:

- delete todos branch menos main:
git branch -r | grep 'origin' | grep -v 'main$' | grep -v HEAD | cut -d/ -f2- | while read line; do git push origin :heads/$line; done;
- delete todos remote tags:
git push origin --delete $(git tag -l)
- delete todos local tag:
git tag -l | xargs git tag -d

Otra prueba. se borro todos los tags/branches

1. Todo configurado como saldremos PROD
- fix: 01 - 2nd

- agregar breaking change


// otro inicio:

- crear 1.0.0

subir a 2.0.0
en 1.0.x -> fix: a fix

merge 1.0.x en main

first beta from master v2.0.1
first beta from master v2.0.1 - 2nd try


en main desde 2.1.0 hacia 2.1.1

iniciando todo de nuevo: fix 001

continuando en main cambio 002
continuando en main cambio 003

test breaking change
primer cambio en beta

rompiendo breaking change on beta from 1.0.1  hacia 2.x

test fix si cambia version de beta
test feat si cambia version de betas
test breaking change on beta


de nuevo iniciando enbranch

main hacia 1.1
main hacia 1.2git

//
inicio de todo nuevamente
feat: 01
feat: 02

//
en 1.0.x

// 1.1.x -> fix: 001

// test send to sontype cloud

```




