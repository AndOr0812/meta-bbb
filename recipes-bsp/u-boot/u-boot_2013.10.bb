require u-boot.inc

# To build u-boot for your machine, provide the following lines in your machine
# config, replacing the assignments as appropriate for your machine.
# UBOOT_MACHINE = "omap3_beagle_config"
# UBOOT_ENTRYPOINT = "0x80008000"
# UBOOT_LOADADDRESS = "0x80008000"

FILESEXTRAPATHS_prepend := "${THISDIR}/files"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"
SECTION = "bootloader"

# This revision corresponds to the tag "v2013.10"
# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "183acb700378a8cfc5d50a01a65de93fb2c24586"

PV = "v2013.10+git${SRCPV}"


SRC_URI = "git://git.denx.de/u-boot.git;branch=master"

SRC_URI += "file://uEnv.txt"
S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
